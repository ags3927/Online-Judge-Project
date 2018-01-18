
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

class StreamGobbler implements Runnable {
    InputStream is;
    String type;
    OutputStream os;
    Thread t;
    String str;
    String error;

    StreamGobbler(InputStream is, String type) {
    	this.is = is;
        this.type = type;
        this.os = null;
    	t=new Thread(this);
    } 

    StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
        t = new Thread(this);
    }
    
    StreamGobbler(OutputStream os,String type, InputStream redirect) {
        this.os = os;
        this.type = type;
        this.is = redirect;
        t = new Thread(this);
    }
    
    public void start() {
    	t.start();
    }


    public void run() {
        try {
            PrintWriter pw = null;
            if (os != null) pw = new PrintWriter(os);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ( (line = br.readLine()) != null)
            {
                if (pw != null) pw.println(line);
                System.out.println(type + ">" + line);
            }
            if (pw != null) pw.flush();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}

public class ProcessExecutor {

    int exitVal;

    ProcessExecutor(){}

    public ProcessExecutor(String processName, String input, String output) {
        try {
            FileOutputStream fos = new FileOutputStream(output);
            FileInputStream fis = new FileInputStream(input);
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(processName);
            StreamGobbler errorGobbler = new  StreamGobbler(proc.getErrorStream(), "Error");
            StreamGobbler inputGobbler = new  StreamGobbler(proc.getOutputStream(),"Input", fis);
            StreamGobbler outputGobbler = new  StreamGobbler(proc.getInputStream(), "Output", fos);

            errorGobbler.start();
            inputGobbler.start();
            outputGobbler.start();
            boolean finished = proc.waitFor(1, TimeUnit.SECONDS);
            //System.out.println(finished);
            if(!finished){ exitVal = -2; }
            else {exitVal = proc.waitFor();}
            exitVal = proc.waitFor();
            //System.out.println("ExitValue for "+ processName +" : " + exitVal);
            fis.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //6
    }

    static String readFile(String path) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    void Compilation(File code) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(code));
        BufferedWriter bw = new BufferedWriter(new FileWriter("problem.cpp"));
        while (true){
            String data = br.readLine();
            if (data == null)
                break;
            bw.write(data+"\n");
        }
        br.close();
        bw.close();

        ProcessExecutor p1 = new ProcessExecutor("g++ -o b problem.cpp", "E:\\Project_CSE_1-2\\Host Of Online Judge\\in.txt" , "E:\\Project_CSE_1-2\\Host Of Online Judge\\out.txt");
        ProcessExecutor p2 = null;
        File file = new File("b.exe");
        boolean exists = file.exists();
        if(!exists){
            System.out.println("Compilation Error.");
            return;
        }
        p2 = new ProcessExecutor("b.exe", "E:\\Project_CSE_1-2\\Host Of Online Judge\\in.txt", "E:\\Project_CSE_1-2\\Host Of Online Judge\\out.txt");
        String userSol = null;
        try {
            userSol = readFile("E:\\Project_CSE_1-2\\Host Of Online Judge\\out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sysSol = null;
        try {
            sysSol = readFile("E:\\Project_CSE_1-2\\Host Of Online Judge\\sol.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(userSol);
        System.out.println(sysSol);

        int temp;
        if(p2!=null){temp=p2.exitVal;}
        else{temp=0;}
        System.out.println(temp);
        if(userSol.equals(sysSol)){
            System.out.println("Correct Answer.");
        }
        else
        {
            switch(temp)
            {
                case 0:
                    System.out.println("Wrong Answer.");
                    break;
                case -2:
                    System.out.println("TLE");
                    break;
                default:
                    System.out.println("RTE");
            }
        }
    }
}
