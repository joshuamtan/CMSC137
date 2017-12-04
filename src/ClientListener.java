import java.net.*;
import java.io.*;
import java.util.*;

public class ClientListener extends Thread{
    private Socket server;
    private ArrayList<String> messages;
    
    public ClientListener(Socket server){
        this.server = server;
        messages = new ArrayList<String>();
    }

    public void run(){
        while(true){
            try{
                InputStream inFromServer = this.server.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                String incoming = in.readUTF();
                System.out.println(incoming);
                messages.add(incoming);
                // write to textbox in game screen
            } catch(Exception e){
                System.out.println("Cannot find (or disconnected from) Server");
                break;
            }
            
        }
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
}