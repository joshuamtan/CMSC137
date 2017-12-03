import java.io.Serializable;
import java.net.InetAddress;

public class Player implements Serializable {
    private InetAddress address;
    private String name;
    private int port;
    private int score;
    private Snake snake = null;     // snake representation of player

    public Player(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.score = 0;
        snake = new Snake();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public Snake getSnake() {
        return snake;
    }
}
