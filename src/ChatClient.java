
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatClient extends Thread implements Constants {
    Socket server = null;
    String username = "";
    String address = "";
    Input input = null;
    Thread listenerThread;

    public ChatClient(String serverName, String username) {
        this.input = input;
        this.username = username;

        try {
            /* Open a ClientSocket and connect to ServerSocket */
            server = new Socket(serverName, CHAT_PORT);
            address = server.getLocalSocketAddress().toString();
            System.out.println("Successfully created chat client at " + address + " Username: " + username);
            listenerThread = new ClientListener(server);
            listenerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
    }

    public void sendMessage(String message) {
        try {
            OutputStream outToServer = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(username + ": " + message);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ClientListener getListener() {
        return (ClientListener) listenerThread;
    }
}
