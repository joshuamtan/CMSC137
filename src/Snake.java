import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class Snake {
    public int health = 200;
    public float snakeX = 240;
    public float snakeY = 500;
    public float snakeSize = 25;

    public Snake(){

    }

    public void render(Graphics g){
        g.fillOval(snakeX, snakeY, snakeSize, snakeSize);
        g.drawString(Integer.toString(health), snakeX+30, snakeY);
    }

    public void update(GameContainer gameContainer){
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_LEFT) && snakeX > 0){snakeX -= .5; }
        if (in.isKeyDown(Input.KEY_RIGHT) && snakeX < GameClient.gameWidth - snakeSize){snakeX += .5; }
    }


}
