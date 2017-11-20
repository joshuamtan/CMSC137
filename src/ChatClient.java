
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatClient{
    public static void main(String [] args){
        boolean connected = true;
        try{
            String serverName = args[0]; //get IP address of server from first param
            int port = Integer.parseInt(args[1]); //get port from second param
            String name = args[2]; //get username from the third param
            String message = null;
            /* Open a ClientSocket and connect to ServerSocket */
            Socket server = new Socket(serverName, port);
            
            /* Receive data from the ServerSocket */
            Thread listenerThread = new ClientListener(server);
            listenerThread.start();
                
            while(true){
                /* Send data to the ServerSocket */
                Scanner scan = new Scanner(System.in);
                message = scan.nextLine();
                OutputStream outToServer = server.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF(name+": " +message);

            }

        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Cannot find (or disconnected from) Server");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: java ChatClient <server ip> <port no.> '<your message to the server>'");
        }  
    }
}
