import java.io.Serializable;

public class PlayerUpdatePacket implements Serializable {
    private String name;
    private int score;
    private Snake snake;

    public PlayerUpdatePacket(String name, int score, Snake snake) {
        this.name = name;
        this.score = score;
        this.snake = snake;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Snake getSnake() {
        return snake;
    }
}
