import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    static int num=0;
    static ArrayList<String>lang = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(53333);
            Socket socket = serverSocket.accept();
            NetworkUtil nc = new NetworkUtil(socket);
            new ReadThreadServer(nc);
        }
    }
}
