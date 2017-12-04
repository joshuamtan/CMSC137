
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer extends Thread implements Constants{
    private ServerSocket serverSocket;
    private ArrayList<Socket> clients;

    public ChatServer() throws IOException{
        //binding a socket to a port
        serverSocket = new ServerSocket(CHAT_PORT);
        clients = new ArrayList<>();
        // serverSocket.setSoTimeout(10000);
    }

    public void run(){
        while(true){
            try{
                /* Accept clients connecting */
                Socket client = serverSocket.accept();
                clients.add(client);

                /* Send data to the ClientSocket */
                Thread t1 = new SearchMessages(serverSocket, client, clients);
                t1.start();
            }catch(SocketTimeoutException s){
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Input/Output Error!");
                //possible cause: client was disconnected while waiting for input
                break;
            }
        }
    }
}
