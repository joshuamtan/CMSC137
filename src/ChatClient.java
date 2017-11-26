
import org.newdawn.slick.Input;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatClient extends Thread implements Constants {
    Socket server = null;
    String message = "";
    String name = "";
    Input input = null;

    public ChatClient(String serverName, Input input) {
        this.input = input;

        try {
            /* Open a ClientSocket and connect to ServerSocket */
            server = new Socket(serverName, CHAT_PORT);
            name = server.getLocalSocketAddress().toString();
            System.out.println("Successfully created client " + name);
            Thread listenerThread = new ClientListener(server);
            listenerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            // getting input from user
            if (message.length() < 32) {

            }
            if (input.isKeyPressed(Input.KEY_A)) { message += "a"; }
            if (input.isKeyPressed(Input.KEY_B)) { message += "b"; }
            if (input.isKeyPressed(Input.KEY_C)) { message += "c"; }
            if (input.isKeyPressed(Input.KEY_D)) { message += "d"; }
            if (input.isKeyPressed(Input.KEY_E)) { message += "e"; }
            if (input.isKeyPressed(Input.KEY_F)) { message += "f"; }
            if (input.isKeyPressed(Input.KEY_G)) { message += "g"; }
            if (input.isKeyPressed(Input.KEY_H)) { message += "h"; }
            if (input.isKeyPressed(Input.KEY_I)) { message += "i"; }
            if (input.isKeyPressed(Input.KEY_J)) { message += "j"; }
            if (input.isKeyPressed(Input.KEY_K)) { message += "k"; }
            if (input.isKeyPressed(Input.KEY_L)) { message += "l"; }
            if (input.isKeyPressed(Input.KEY_M)) { message += "m"; }
            if (input.isKeyPressed(Input.KEY_N)) { message += "n"; }
            if (input.isKeyPressed(Input.KEY_O)) { message += "o"; }
            if (input.isKeyPressed(Input.KEY_P)) { message += "p"; }
            if (input.isKeyPressed(Input.KEY_Q)) { message += "q"; }
            if (input.isKeyPressed(Input.KEY_R)) { message += "r"; }
            if (input.isKeyPressed(Input.KEY_S)) { message += "s"; }
            if (input.isKeyPressed(Input.KEY_T)) { message += "t"; }
            if (input.isKeyPressed(Input.KEY_U)) { message += "u"; }
            if (input.isKeyPressed(Input.KEY_V)) { message += "v"; }
            if (input.isKeyPressed(Input.KEY_W)) { message += "w"; }
            if (input.isKeyPressed(Input.KEY_X)) { message += "x"; }
            if (input.isKeyPressed(Input.KEY_Y)) { message += "y"; }
            if (input.isKeyPressed(Input.KEY_Z)) { message += "z"; }
            if (input.isKeyPressed(Input.KEY_1)) { message += "1"; }
            if (input.isKeyPressed(Input.KEY_2)) { message += "2"; }
            if (input.isKeyPressed(Input.KEY_3)) { message += "3"; }
            if (input.isKeyPressed(Input.KEY_4)) { message += "4"; }
            if (input.isKeyPressed(Input.KEY_5)) { message += "5"; }
            if (input.isKeyPressed(Input.KEY_6)) { message += "6"; }
            if (input.isKeyPressed(Input.KEY_7)) { message += "7"; }
            if (input.isKeyPressed(Input.KEY_8)) { message += "8"; }
            if (input.isKeyPressed(Input.KEY_9)) { message += "9"; }
            if (input.isKeyPressed(Input.KEY_0)) { message += "0"; }
            if (input.isKeyPressed(Input.KEY_SPACE)) { message += " "; }
            if (input.isKeyPressed(Input.KEY_BACK)) {
                if (message.length() > 0) message = message.substring(0,message.length()-1);
            }
            if (input.isKeyPressed(Input.KEY_ENTER)) {
                sendMessage();
                message = "";
            }

            PlayScreen.message = this.message;
        }
    }

    public void sendMessage() {
        try {
            System.out.println("Sending message");
            OutputStream outToServer = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(name + ": " + message);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
//        try{
//            String serverName = args[0]; //get IP address of server from first param
//            int port = Integer.parseInt(args[1]); //get port from second param
//            String name = args[2]; //get username from the third param
//            String message = null;
            /* Open a ClientSocket and connect to ServerSocket */
//            Socket server = new Socket(serverName, port);
            
            /* Receive data from the ServerSocket */
//            Thread listenerThread = new ClientListener(server);
//            listenerThread.start();
                
//            while(true){
//                /* Send data to the ServerSocket */
//                Scanner scan = new Scanner(System.in);
//                message = scan.nextLine();
//                OutputStream outToServer = server.getOutputStream();
//                DataOutputStream out = new DataOutputStream(outToServer);
//                out.writeUTF(name+": " +message);
//
//            }

//        }catch(IOException e){
//            e.printStackTrace();
//            System.out.println("Cannot find (or disconnected from) Server");
//        }catch(ArrayIndexOutOfBoundsException e){
//            System.out.println("Usage: java ChatClient <server ip> <port no.> '<your message to the server>'");
//        }
}
