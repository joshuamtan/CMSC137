
import java.net.*;
import java.io.*;
import java.util.*;

public class SearchMessages extends Thread{
    private ServerSocket serverSocket;
    private Socket client;
    private ArrayList<Socket> clients;

    public SearchMessages(ServerSocket serverSocket, Socket client, ArrayList<Socket> clients) throws IOException{
        this.serverSocket = serverSocket;
        this.client = client;
        this.clients = clients;
    }

    public void run(){
        while(true){
            try{
                DataInputStream in = new DataInputStream(this.client.getInputStream());
                String message = in.readUTF();
//                System.out.println(message); //readUTF waits for input
                for(Socket socket : clients){
//                    if(socket.getInetAddress() != this.client.getInetAddress()){
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF(message);
//                    }
                }
                
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