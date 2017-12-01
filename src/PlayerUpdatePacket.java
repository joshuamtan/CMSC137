import java.io.Serializable;

public class PlayerUpdatePacket implements Serializable {
    private String name;
    private int score;

    public PlayerUpdatePacket(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
