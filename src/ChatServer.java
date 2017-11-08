import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;       
import java.util.*;
import java.awt.event.*;

public class ChatServer{

  //server socket
  private static ServerSocket serverSocket = null;
  //client socket
  private static Socket clientSocket = null;

  //Server can accept up to 100000 client connections
  private static final int maxClientsCount = 100000;
  private static final clientThread[] threads = new clientThread[maxClientsCount];

  public static void main(String args[]) {

    int portNumber = 2222;
    if (args.length < 1) {
      System.out.println("Chat Server now running!\n");
    } else {
      portNumber = Integer.valueOf(args[0]).intValue();
    }

    //opens a server socket on port 2222
    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    //Creates a client socket for each connection and passes it to a new client thread
    while (true) {
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxClientsCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new clientThread(clientSocket, threads)).start();
            break;
          }
        }

        //if the client count reaches maxClientCount
        //close
        if (i == maxClientsCount) {
          PrintStream streamOut = new PrintStream(clientSocket.getOutputStream());
          streamOut.println("Server too busy. Try later.");
          streamOut.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}

//This client thread opens the input and output streams for a specific client.
class clientThread extends Thread {

  private BufferedReader streamIn = null;
  private PrintStream streamOut = null;
  private Socket clientSocket = null;
  private final clientThread[] threads;
  private int maxClientsCount;

  public clientThread(Socket clientSocket, clientThread[] threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    maxClientsCount = threads.length;
  }

  public void run() {
    int maxClientsCount = this.maxClientsCount;
    clientThread[] threads = this.threads;

    try {
      //creates input and output streams for the client
      streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      streamOut = new PrintStream(clientSocket.getOutputStream());

      //gets the client's name
      JFrame frame = new JFrame("InputDialog Example #1");
      String name = streamIn.readLine();

      //prints the name of every client that joins
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].streamOut.println("*** A new user " + name
              + " entered the chat room !!! ***");
        }
      }


      while (true) {

      	String line = streamIn.readLine();
        
        //quits when "/quit" is entered
        if (line.startsWith("/quit")) {
          break;
        }

        //outputs the message of each client
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null) {
            threads[i].streamOut.println("<" + name + "> " + line);
          }
        }
      }

      //informs when another client quits
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].streamOut.println("*** The user " + name
              + " is leaving the chat room !!! ***");
        }
      }
      streamOut.println("*** Bye " + name + " ***");

      //sets up the current thread to null so a new client can be accepted by the server
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] == this) {
          threads[i] = null;
        }
      }

      streamIn.close();
      streamOut.close();
      clientSocket.close();
    } catch (IOException e) {
    }
  }
}