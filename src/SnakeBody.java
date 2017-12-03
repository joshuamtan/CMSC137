import org.newdawn.slick.Graphics;

public class SnakeBody {
    public float xpos;
    public float ypos;
    public float snakeSize;

    public SnakeBody(float prevX, float prevY, float snakeSize){
        xpos = prevX;
        ypos = prevY;
        this.snakeSize = snakeSize;
    }

    public void render(Graphics g){
        g.fillOval(xpos, ypos, snakeSize, snakeSize);
    }

    public float getXpos() {
        return xpos;
    }

    public void increaseXpos(float inc){
        xpos += inc;
    }

    public void decreaseXpos(float inc){
        xpos -= inc;
    }
    public float getYpos() {
        return ypos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }
}
