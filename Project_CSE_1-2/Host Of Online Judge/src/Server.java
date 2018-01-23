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

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(53333);
        while (true) {
            Socket socket = serverSocket.accept();
            NetworkUtil nc = new NetworkUtil(socket);
            System.out.println("poop");
            new ReadThreadServer(nc);
        }
    }
}