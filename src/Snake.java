import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Timer;

public class Snake implements Constants {
    public int health = 50;
    public float snakeHeadX = 240;
    public float snakeHeadY = 500;
    public float snakeBodyX = 240;
    public float snakeBodyY = 240;
    public float snakeSize = 25;

    public Snake(){}

    public void render(Graphics g){
        g.drawString(Integer.toString(health), snakeHeadX+30, snakeHeadY);
        g.fillOval(snakeHeadX, snakeHeadY+snakeSize, snakeSize, snakeSize);
        for(int i=1; i<health/5; i++){
            g.fillOval(snakeHeadX, snakeHeadY+(snakeSize*i), snakeSize, snakeSize);
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame game){
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_LEFT) && snakeHeadX > 0){
            snakeHeadX -= .5;
        }
        if (in.isKeyDown(Input.KEY_RIGHT) && snakeHeadX < GAME_WIDTH - snakeSize){
            snakeHeadX += .5;
        }
        if (in.isKeyDown(Input.KEY_UP) && snakeHeadY > 0){snakeHeadY -= .5; }
        if (in.isKeyDown(Input.KEY_DOWN) && snakeHeadY < WINDOW_HEIGHT - snakeSize){snakeHeadY += .5; }


        if(this.health <= 0){
            game.enterState(GAME_OVER_STATE);
        }
    }
}
