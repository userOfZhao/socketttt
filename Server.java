package BioChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaofeng on 2016/10/21.
 */
public class Server {
    private static ServerSocket serverSocket = null;
    private static int port = 12345;

    public static void main(String[] args) {
        System.out.println("start server now");
        try{
            serverSocket = new ServerSocket(port);
            ExecutorService exec = Executors.newCachedThreadPool();
            while(true) {
                Socket socket = serverSocket.accept();
                exec.execute(new ServerThread(socket));
                Thread.sleep(10000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
