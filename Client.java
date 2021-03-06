package BioChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zhaofeng on 2016/10/21.
 */
public class Client {

    public static void main(String[] args) {
        Scanner scanner = null;
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        int port = 12345;
        Thread thread = null;
        String host = "127.0.0.1";
        try {
            scanner = new Scanner(System.in);
            socket = new Socket(host, port);
            out = socket.getOutputStream();
            //in = socket.getInputStream();
            out.write("hello~".getBytes());
            thread = new ReadTread(socket);
            String str = null;
            while(!(str = scanner.nextLine()).equals("#")) {
                out.write(str.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                thread.interrupt();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
