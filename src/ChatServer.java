
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer extends Thread{
    private ServerSocket serverSocket;
    private ArrayList<Socket> clients;

    public ChatServer(int port) throws IOException{
        //binding a socket to a port
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
        // serverSocket.setSoTimeout(10000);
    }

    public void run(){
        boolean connected = true;
        while(connected){
            try{
                /* Read data from the ClientSocket */
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
    public static void main(String [] args){
        try{
            int port = Integer.parseInt(args[0]);
            Thread t = new ChatServer(port);
            t.start();
        }catch(IOException e){
            //e.printStackTrace();
            System.out.println("Usage: java ChatServer <port no.>\n"+
                    "Make sure to use valid ports (greater than 1023)");
        }catch(ArrayIndexOutOfBoundsException e){
            //e.printStackTrace();
            System.out.println("Usage: java ChatServer <port no.>\n"+
                    "Insufficient arguments given.");
        }
    }
}
