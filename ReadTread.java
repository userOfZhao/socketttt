package BioChat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


/**
 * Created by zhaofeng on 2016/10/21.
 */
public class ReadTread extends Thread {
    InputStream input = null;
    Socket socket = null;

    public ReadTread(Socket socket) {
        this.socket = socket;
        try {
            this.input = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run() {
        System.out.println("start server thread");
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while(true) {
                len = input.read(bytes);
                System.out.println("Server recive is " + new String(bytes, 0, len));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
