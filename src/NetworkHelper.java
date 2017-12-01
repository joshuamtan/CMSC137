import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;


public class NetworkHelper implements Constants {
    private static InetAddress host;
    private static boolean isHost = false;
    private static boolean isServer = false;
    private static MulticastSocket clientSocket = null;

    static {
        // create a client
        try {
            int port = 1024 + ((int) Math.random() * GAME_CLIENT_PORT);

            clientSocket = new MulticastSocket(port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Thread createClientReceiver() {
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    try {
                        clientSocket.receive(packet);

                        Object rawData = Serializer.toObject(packet.getData());
                        Packet received = (Packet) rawData;

                        InetAddress sourceAddress = packet.getAddress();
                        int port = packet.getPort();

                        System.out.println("[Client] Received " + received.getType() + "from" + sourceAddress + ":" + port);

                        switch(received.getType()) {
                        }

                    } catch (Exception e) {}

                }
            }
        };

        return thread;
    }

    public static Thread createServerReceiver() {
        Thread thread = new Thread() {
            public void run() {
                isServer = true;

                while (true) {
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    try {
                        // get data from players
                        MulticastSocket serverSocket = new MulticastSocket(GAME_PORT);
                        serverSocket.receive(packet);
                        serverSocket.close();

                        // get data from players
                        Object rawData = Serializer.toObject(packet.getData());
                        Packet received = (Packet) rawData;

                        InetAddress sourceAddress = packet.getAddress();
                        int port = packet.getPort();

                        System.out.println("[Server] Received " + received.getType() + " from " + sourceAddress + ":" + port);

                        switch(received.getType()) {
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        return thread;
    }

    public static void serverSend(Packet packet, InetAddress destination, int port) {
        try {
            DatagramPacket datagramPacket;
            byte buf[] = Serializer.toBytes(packet);
            datagramPacket = new DatagramPacket(buf, buf.length, destination, port);

            MulticastSocket ms = new MulticastSocket(GAME_PORT);
            ms.send(datagramPacket);

            System.out.println("[Server] Sending " + packet.getType() + "from" + destination + ":" + port);
            ms.close();
        } catch (Exception e) {}
    }

    public static void clientSend(Packet packet, InetAddress destination) {
        try {
            DatagramPacket datagramPacket;
            byte buf[] = Serializer.toBytes(packet);
            datagramPacket = new DatagramPacket(buf, buf.length, destination, GAME_PORT);

            clientSocket.send(datagramPacket);
            System.out.println("[Client] Sending " + packet.getType() + " to " + destination);
        } catch (Exception e) {}
    }

    public static void connect(String host, String name) {
        try {
            InetAddress address = InetAddress.getByName(host);
            NetworkHelper.host = address;

            NetworkHelper.clientSend(new Packet("CONNECT", new ConnectPacket(name)), address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Getters and setters
    * */

    public static InetAddress getHost() {
        return NetworkHelper.host;
    }

    public static void setIsHost(boolean isHost) {
        NetworkHelper.isHost = isHost;
    }

    public static boolean isHost() {
        return isHost;
    }
}
