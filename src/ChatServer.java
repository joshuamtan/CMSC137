import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;       
import java.util.*;
import java.awt.event.*;

public class ChatServer{


  private static ServerSocket serverSocket = null;
  private static Socket clientSocket = null;


  private static final int maxClientsCount = 10;
  private static final clientThread[] threads = new clientThread[maxClientsCount];

 	Container container;
	JPanel body;
	JPanel chatWindow;
	JPanel messagePanel;
	JButton sendButton;
	JTextField messageBox;

  public static void main(String args[]) {

    int portNumber = 2222;
    if (args.length < 1) {
      System.out.println("Chat Server now running!\n");
    } else {
      portNumber = Integer.valueOf(args[0]).intValue();
    }


    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }


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

class clientGUI extends JFrame implements ActionListener{
	Container container;
	JPanel body;
	JTextArea chatWindow;
	JPanel messagePanel;
	JButton sendButton;
	JTextField messageBox;
	
	public clientGUI(){
		container = this.getContentPane();
	
		body = new JPanel();
		chatWindow = new JTextArea();
		messagePanel = new JPanel();
		sendButton = new JButton("SEND");
		messageBox = new JTextField();
		
		body.setBorder(BorderFactory.createEmptyBorder(50,70,50,70));
		body.setLayout(new BorderLayout());
		messagePanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setPreferredSize(new Dimension(250, 100));
		chatWindow.setPreferredSize(new Dimension(500, 270));
		chatWindow.setBackground(Color.blue);
		messageBox.setPreferredSize(new Dimension (300, 100));
		sendButton.setPreferredSize(new Dimension(150, 100));
		
		messagePanel.add(sendButton, BorderLayout.EAST);
		messagePanel.add(messageBox, BorderLayout.WEST);
		body.add(chatWindow, BorderLayout.NORTH);
		body.add(messagePanel, BorderLayout.SOUTH);
		container.add(body);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 500);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == sendButton){
			String line = messageBox.getText();
			chatWindow.append(line);
		}
	}
}


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
     
      streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      streamOut = new PrintStream(clientSocket.getOutputStream());


      JFrame frame = new JFrame("InputDialog Example #1");
      String name = streamIn.readLine();
   	  // clientGUI client = new clientGUI();
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].streamOut.println("*** A new user " + name
              + " entered the chat room !!! ***");
        }
      }
      while (true) {

      	 String line = streamIn.readLine();
  
        if (line.startsWith("/quit")) {
          break;
        }
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null) {
            threads[i].streamOut.println("<" + name + "> " + line);
             // threads[i].chatWindow.append("<" + name + "> " + line);
          }
        }
      }
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].streamOut.println("*** The user " + name
              + " is leaving the chat room !!! ***");
        }
      }
      streamOut.println("*** Bye " + name + " ***");


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