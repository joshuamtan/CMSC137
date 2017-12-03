import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;

public class Snake implements Constants, Serializable {
    public int health = 50;
    public float snakeHeadX = 240;
    public float snakeHeadY = 500;
    public float snakeBodyX = 240;
    public float snakeBodyY = 240;
    public float snakeSize = 25;
    ArrayList<SnakeBody> snakeBody = null;
    public Snake(){
        snakeBody = new ArrayList<>();
        for(int i=1; i<health/5; i++){
            SnakeBody cell = new SnakeBody(snakeHeadX, snakeHeadY+(snakeSize*i), snakeSize);
            snakeBody.add(cell);
        }

    }

    public void render(Graphics g){
        g.drawString(Integer.toString(health), snakeHeadX+30, snakeHeadY);
        for(int i=1; i<snakeBody.size(); i++){
            snakeBody.get(i).render(g);
            snakeBody.get(i).setYpos(snakeHeadY+snakeSize*i);
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

    public void moveBody(int i){
        if(i == 0){
            if (snakeBody.get(i).getXpos() < snakeHeadX){
                while(snakeBody.get(i).getXpos() < snakeHeadX){
                    snakeBody.get(i).increaseXpos((float).5);
                }
            }
            if (snakeBody.get(i).getXpos() > snakeHeadX){
                while(snakeBody.get(i).getXpos() > snakeHeadX){
                    snakeBody.get(i).decreaseXpos((float).5);
                }
            }
        }else{
            if (snakeBody.get(i).getXpos() < snakeBody.get(i-1).getXpos()){
                while(snakeBody.get(i).getXpos() < snakeBody.get(i-1).getXpos()){
                    snakeBody.get(i).increaseXpos((float).5);
                }
            }
            if (snakeBody.get(i).getXpos() > snakeBody.get(i-1).getXpos()){
                while(snakeBody.get(i).getXpos() > snakeBody.get(i-1).getXpos()){
                    snakeBody.get(i).decreaseXpos((float).5);
                }
            }

        }


    }

    public void checkSnakeBody(){

    }
}
