import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String [] args)
    {   
        try
        {
            System.out.println("Connecting to localhost on port 4444");
            Socket socket= new Socket("127.0.0.1", 4444);
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
                System.out.println("Client:  "+str);
                System.out.println("Server:  "+in.readUTF());
                str=sin.readLine();
            }

            out.close();
            in.close();
            socket.close();
        }
        catch(IOException e)
        {
            System.out.println("Error"+e);
        }

    }
}