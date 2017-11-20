import java.net.*;
import java.io.*;
import java.util.*;

public class ClientListener extends Thread{
    private Socket server;
    
    public ClientListener(Socket server){
        this.server = server;
    }

    public void run(){
        while(true){
            try{
                InputStream inFromServer = this.server.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println(in.readUTF());
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Cannot find (or disconnected from) Server");
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your message to the server>'");
            }
            
        }
    }


}