import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class Snake {
    public int health = 200;
    public float snakeX = 240;
    public float snakeY = 500;

    public Snake(){

    }

    public void render(Graphics g){
        g.fillOval(snakeX, snakeY, 25, 25);
        g.drawString(Integer.toString(health), snakeX+30, snakeY);
    }

    public void update(GameContainer gameContainer){
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_LEFT)){snakeX -= .5; }
        if (in.isKeyDown(Input.KEY_RIGHT)){snakeX += .5; }
    }


}
