import java.net.*;
import java.io.*;

public class Server
{
    public static void main(String [] args)
    {   
        try
        {   

            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Waiting for client on port "+serverSocket.getLocalPort()+"...");
            Socket socket = serverSocket.accept();
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outToServer = socket.getOutputStream();
            InputStream inFromServer = socket.getInputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            DataInputStream in = new DataInputStream(inFromServer);

            String str;
            str = sin.readLine();
            while(!str.equals("bye")){
                out.writeUTF(str);
                System.out.println("Server:  "+str);
                System.out.println("Client:  "+in.readUTF());
                str=sin.readLine();
            }

            out.close();
            in.close();
            socket.close();
            serverSocket.close(); 
        }
        catch(IOException e)
        {
            System.out.println("Error"+e);
        }

    }
}