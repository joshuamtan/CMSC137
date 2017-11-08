import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;       
import java.util.*;
import java.awt.event.*;

public class ChatClient implements Runnable {

  // The client socket
  private static Socket clientSocket = null;
  // The output stream
  private static PrintStream streamOut = null;
  // The input stream
  private static BufferedReader streamIn = null;

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  
  public static void main(String[] args) {

    // The default port.
    int portNumber = 2222;
    // The default host.
    String host = "localhost";

    if (args.length < 2) {
      System.out
          .println("Usage: java ChatClient <host> <portNumber>\n"
              + "Now using host=" + host + ", portNumber=" + portNumber);
    } else {
      host = args[0];
      portNumber = Integer.valueOf(args[1]).intValue();
    }

    //Opens socket on a given host and port.
    //Opens input and output streams.
    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      streamOut = new PrintStream(clientSocket.getOutputStream());
      streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + host);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host "
          + host);
    }

    //gets and writes data to the opened socket
    if (clientSocket != null && streamOut != null && streamIn != null) {
      try {

        /* Create a thread to read from the server. */
        new Thread(new ChatClient()).start();
        while (!closed) {
          streamOut.println(inputLine.readLine().trim());
        }
        /*
         * Close the output stream, close the input stream, close the socket.
         */
        streamOut.close();
        streamIn.close();
        clientSocket.close();
      } catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
  }


  public void run() {

    String responseLine;

    //gets the name of the client
    try {
      JFrame frame = new JFrame("InputDialog Example #1");
      String name = JOptionPane.showInputDialog(frame, "What's your name?");
      PrintStream out = new PrintStream(clientSocket.getOutputStream(), true);
      out.println(name);
      BufferedReader in = new BufferedReader(new
      InputStreamReader(clientSocket.getInputStream()));
      System.out.println(in.readLine());

      //keeps reading from the socket till client quits and receives Bye from server
      while ((responseLine = streamIn.readLine()) != null) {
        System.out.println(responseLine);
        if (responseLine.indexOf("*** Bye") != -1)
          break;
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
    System.exit(0);
  }
}