
package BioChat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by zhaofeng on 2016/10/21.
 */
public class ServerThread implements Runnable {
    Socket socket = null;
    Scanner scanner = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        byte[] bytes = new byte[1024];
        InputStream in = null;
        OutputStream out = null;
        Thread thread = null;
        try {
            out = socket.getOutputStream();
           // in = socket.getInputStream();
            thread = new ReadTread(socket);

            //int len = in.read(bytes);
            //System.out.println("Server recive is " + new String(bytes, 0, len));
            scanner = new Scanner(System.in);
            String str = null;
            while(!(str = scanner.nextLine()).equals("#")) {
                System.out.println("server input is " + str);
                out.write(str.getBytes());
            }

        } catch (Exception e) {
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
