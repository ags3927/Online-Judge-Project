import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.*;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public class ReadThreadServer implements Runnable{
    NetworkUtil nc;

    public ReadThreadServer(NetworkUtil nc) {
        this.nc = nc;
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            Object object = nc.read();
            SubmitData temp = null;
            if (object instanceof LogInData){
                String userName = ((LogInData) object).getHandle();
                String pass = ((LogInData) object).getPassword();
                String info = null;
                try {
                    info = SignIn(userName, pass);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (info == null)
                    info = "Found";
                nc.write(info);
                if(info.equals("Found")==false){
                    String s[] = info.split("\t\t");
                    try {
                        File file = new File(s[1]+".txt");
                        SubmissionHistory submissionHistory = new SubmissionHistory(file);
                        nc.write(submissionHistory);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            if (object instanceof SignUpData) {
                String email = ((SignUpData) object).getEmail();
                String name = ((SignUpData) object).getName();
                String handle = ((SignUpData) object).getHandle();
                String password = ((SignUpData) object).getPassword();
                Object b = SignUp(email, name, handle, password);
                nc.write(b);
                try {
                    File file = new File(handle+".txt");
                    SubmissionHistory submissionHistory = new SubmissionHistory(file);
                    nc.write(submissionHistory);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(object instanceof SubmitData) {
                temp = (SubmitData) object;
                String handle = nc.read().toString();
                String lang = temp.getLanguage();
                String verdict = null;
                if(lang.equalsIgnoreCase("C++")||lang.equalsIgnoreCase("C")) {
                    synchronized (this){

                        ProcessExecutor processExecutor = new ProcessExecutor();
                        try {
                            int verdictVal = processExecutor.CompilationCpp(temp);
                            verdict = null;
                            verdict = verdictSet(verdictVal, verdict);
                            nc.write(new VerdictData(verdict, temp.getProblem()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        SubmissionHistory submissionHistory = update(handle,temp,verdict);
                        nc.write(submissionHistory);
                    }
                }
                else if(lang.equalsIgnoreCase("Java")){
                    synchronized (this) {
                        ProcessExecutor processExecutor = new ProcessExecutor();
                        try {
                            int verdictVal = processExecutor.CompilationJava(temp);
                            verdict = null;
                            verdict = verdictSet(verdictVal, verdict);
                            nc.write(new VerdictData(verdict, temp.getProblem()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        SubmissionHistory submissionHistory = update(handle,temp,verdict);
                        nc.write(submissionHistory);
                    }
                }

            }
            if (object instanceof ContestProblem){
                System.out.println("YES");
            }
            if (object.toString().equals("BreakUp")){
                nc.closeConnection();
            }
            if (object.toString().equals("Blogs")){
                BlogObject bo = new BlogObject();
                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("title.txt"));
                    BufferedReader br2 = new BufferedReader(new FileReader("dis.txt"));

                    while (true){
                        String s1 = br1.readLine();
                        String s2 = br2.readLine();

                        if (s1 == null)
                            break;
                        bo.title.add(s1);
                        bo.dis.add(s2);
                    }
                    br1.close();
                    br2.close();
                    nc.write(bo);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (object.toString().equals("AddBlog")){
                String s1 = (String) nc.read();
                String s2 = (String) nc.read();

                AddFile(s1,s2);
            }
        }
    }

    private String verdictSet(int verdictVal, String verdict) {
        if(verdictVal==0) {
            verdict = "Accepted";
        }
        else if (verdictVal==-1)
        {
            verdict = "Wrong Answer";
        }
        else if(verdictVal==-3)
        {
            verdict = "Compilation Error";
        }
        else if(verdictVal==1)
        {
            verdict = "Runtime Error";
        }
        else if(verdictVal==-2)
        {
            verdict = "Time Limit Exceeded";
        }
        return verdict;
    }

    private static String SignIn(String loginNameText, String pass) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("userDetails.txt"));
        try{
            while (true){
                String data = bufferedReader.readLine();
                if (data == null)
                    break;

                String[] s = data.split("\t\t");
                if((loginNameText.equals(s[1]) || loginNameText.equals(s[2])) && pass.equals(s[3])){
                    return data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufferedReader.close();

        return null;
    }

    private static Object SignUp(String email, String name, String handle, String password){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("userDetails.txt"));
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("tmp.txt"));
            while (true){
                String data = bufferedReader.readLine();
                if (data == null || data.length()<4)
                    break;

                String s[] = data.split("\t\t");
                if(s[1].equals(handle) || s[2].equals(email)){
                    bufferedReader.close();
                    bufferedWriter.close();
                    return null;
                }
                bufferedWriter.write(data+"\n");
            }
            bufferedWriter.write(name+"\t\t"+handle+"\t\t"+email+"\t\t"+password+"\n");
            bufferedReader.close();
            bufferedWriter.close();

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader("tmp.txt"));
            BufferedWriter bufferedWriter1= new BufferedWriter(new FileWriter("userDetails.txt"));
            while (true){
                String data = bufferedReader1.readLine();
                if(data == null)
                    break;
                bufferedWriter1.write(data+"\n");
            }
            bufferedReader1.close();
            bufferedWriter1.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            handle = handle + ".txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(handle));
            bw.write("");
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return "Ok";
    }

    private SubmissionHistory update(String handle, SubmitData temp, String verdict){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(handle+".txt"));
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("tmp.txt"));
            while (true){
                String data = bufferedReader.readLine();
                if (data == null || data.length()<4)
                    break;
                bufferedWriter.write(data+"\n");
            }
            bufferedWriter.write(temp.getLanguage()+"\t\t"+temp.problem+"\t\t"+verdict+"\n");
            bufferedReader.close();
            bufferedWriter.close();

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader("tmp.txt"));
            BufferedWriter bufferedWriter1= new BufferedWriter(new FileWriter(handle+".txt"));
            while (true){
                String data = bufferedReader1.readLine();
                if(data == null)
                    break;
                bufferedWriter1.write(data+"\n");
            }
            bufferedReader1.close();
            bufferedWriter1.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        SubmissionHistory submissionHistory = new SubmissionHistory(new File(handle+".txt"));
        return submissionHistory;
    }

    private void AddFile(String s1, String s2){
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter("tmp.txt"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("tmp1.txt"));
            BufferedReader br1 = new BufferedReader(new FileReader("title.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("dis.txt"));

            while (true) {
                String x1 = br1.readLine();
                String x2 = br2.readLine();

                if (x1 == null)
                    break;

                bw1.write(x1+"\n");
                bw2.write(x2+"\n");
            }
            bw1.write(s1+"\n");
            bw2.write(s2+"\n");

            br1.close();
            br2.close();
            bw1.close();
            bw2.close();

            BufferedWriter ebw1 = new BufferedWriter(new FileWriter("title.txt"));
            BufferedWriter ebw2 = new BufferedWriter(new FileWriter("dis.txt"));
            BufferedReader ebr1 = new BufferedReader(new FileReader("tmp.txt"));
            BufferedReader ebr2 = new BufferedReader(new FileReader("tmp1.txt"));

            while (true) {
                String x1 = ebr1.readLine();
                String x2 = ebr2.readLine();

                if (x1 == null)
                    break;

                ebw1.write(x1+"\n");
                ebw2.write(x2+"\n");
            }
            ebr1.close();
            ebr2.close();
            ebw1.close();
            ebw2.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
