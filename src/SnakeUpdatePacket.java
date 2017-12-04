import java.io.Serializable;
import java.util.ArrayList;

public class SnakeUpdatePacket implements Serializable {
    private String name;
    private float snakeHeadX;
    private float snakeHeadY;
    private ArrayList<SnakeBody> snakeBody;
    private int health;

    public SnakeUpdatePacket(String name, float snakeHeadX, float snakeHeadY, ArrayList<SnakeBody> snakeBody, int health) {
        this.name = name;
        this.snakeHeadX = snakeHeadX;
        this.snakeHeadY = snakeHeadY;
        this.snakeBody = snakeBody;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public float getSnakeHeadX() {
        return snakeHeadX;
    }

    public float getSnakeHeadY() {
        return snakeHeadY;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<SnakeBody> getSnakeBody() {
        return snakeBody;
    }
}
