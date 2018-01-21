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
            System.out.println(finished);
            if(!finished){ exitVal = -2; }
            else { exitVal = proc.waitFor(); }
            exitVal = proc.waitFor();
            System.out.println("ExitValue for "+ processName +" : " + exitVal);
            fis.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static String readFile(String path) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    public int CompilationCpp(SubmitData sub) throws Exception {
        String prob = "Problemset//"+sub.getProblem()+"//input.txt";
        String op = "Problemset//"+sub.getProblem()+"//output.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter("problem.cpp"));
        String data = sub.getSubmission();
        bw.write(data+"\n");
        bw.close();
        File file1  = new File("a.exe");
        if(file1.exists()){ file1.delete();}
        ProcessExecutor p1 = new ProcessExecutor("g++ problem.cpp", prob, op);
        ProcessExecutor p2 = null;
        File file = new File("a.exe");
        boolean exists = file.exists();
        if(!exists){
            System.out.println("Compilation Error.");
            return -3;
        }
        File file3 = new File("out.txt");
        if(file3.exists()){file3.delete();}
        p2 = new ProcessExecutor("a.exe", prob,op);
        String userSol = null;
        try {
            userSol = readFile(op);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sysSol = null;
        try {
            sysSol = readFile("Problemset//"+sub.getProblem()+"//solution.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int temp;
        if(p2!=null){temp=p2.exitVal;}
        else{temp=-1;}
        System.out.println(temp);
        return getVerdict(userSol, sysSol, temp);
    }

    public int CompilationJava(SubmitData sub) throws IOException {
        String prob = "Problemset//"+sub.getProblem()+"//input.txt";
        String op = "Problemset//"+sub.getProblem()+"//output.txt";
        String data = sub.getSubmission();
        BufferedWriter bw = new BufferedWriter(new FileWriter("MyProblem.java"));
        bw.write(data+"\n");
        bw.close();
        File file1 = new File("MyProblem.jar");
        if(file1.exists()){file1.delete();}
        ProcessExecutor p1 = new ProcessExecutor("javac MyProblem.java", prob,op);
        ProcessExecutor p2 = new ProcessExecutor("jar cfm MyProblem.jar manifest.txt MyProblem.class", prob, op);
        File file = new File("MyProblem.jar");
        if(!file.exists()) {
            System.out.println("Compilation Error.");
            return -3;
        }
        System.out.println("poop");
        File file2 = new File(op);
        if(file2.exists()){file2.delete();}
        ProcessExecutor p3 = new ProcessExecutor("java -jar MyProblem.jar", prob, op);
        String userSol = null;
        try {
            userSol = readFile(op);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sysSol = null;
        try {
            sysSol = readFile("Problemset//"+sub.getProblem()+"//solution.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int temp;
        if(p2!=null){temp=p2.exitVal;}
        else{temp=-1;}
        System.out.println(temp);
        return getVerdict(userSol, sysSol, temp);
    }

    int getVerdict(String userSol, String sysSol, int temp)
    {
        if(userSol.equals(sysSol)){
            System.out.println("Correct Answer.");
            return 0;
        }
        else
        {
            switch(temp)
            {
                case 0:
                    System.out.println("Wrong Answer.");
                    return -1;
                case -2:
                    System.out.println("TLE");
                    return -2;
                default:
                    System.out.println("RTE");
                    return 1;
            }
        }
    }
}