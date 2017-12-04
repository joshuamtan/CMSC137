
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatClient extends Thread implements Constants {
    Socket server = null;
    String name = "";
    Input input = null;
    Thread listenerThread;

    public ChatClient(String serverName) {
        this.input = input;

        try {
            /* Open a ClientSocket and connect to ServerSocket */
            server = new Socket(serverName, CHAT_PORT);
            name = server.getLocalSocketAddress().toString();
            System.out.println("Successfully created chat client " + name);
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
            out.writeUTF(name + ": " + message);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ClientListener getListener() {
        return (ClientListener) listenerThread;
    }
}
