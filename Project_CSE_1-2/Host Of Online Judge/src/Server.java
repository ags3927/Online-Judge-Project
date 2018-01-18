import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    static int num=0;
    static ArrayList<File>file = new ArrayList<>();
    static ArrayList<String>lang = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(53333);
            Socket socket = serverSocket.accept();

            while (true) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object object = ois.readObject();

                if (object instanceof LogInData){
                    String userName = ((LogInData) object).getHandle();
                    String pass = ((LogInData) object).getPassword();
                    String info = SignIn(userName, pass);
                    if (info == null)
                        info = "Found";
                    oos.writeObject(info);
                    socket.close();
                    serverSocket.close();
                    break;
                }
                if (object instanceof SignUpData) {
                    String email = ((SignUpData) object).getEmail();
                    String name = ((SignUpData) object).getName();
                    String handle = ((SignUpData) object).getHandle();
                    String password = ((SignUpData) object).getPassword();
                    Object b = SignUp(email, name, handle, password);
                    oos.writeObject(b);
                    socket.close();
                    serverSocket.close();
                    break;
                } else {
                    String s = (String) ois.readObject();
                    Object o = ois.readObject();

                    if (s != null) {
                        lang.add(s);
                        file.add((File) o);
                        ProcessExecutor processExecutor = new ProcessExecutor();
                        processExecutor.Compilation((File)o);
                        System.out.println(s);
                        num++;
                        serverSocket.close();
                        break;
                    }
                    serverSocket.close();
                    break;
                }
            }

        }
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

        return "Ok";
    }
}
