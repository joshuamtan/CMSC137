import java.io.Serializable;
import java.net.InetAddress;

public class Player implements Serializable {
    private InetAddress address;
    private String name;
    private int port;

    public Player(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
