import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.io.Serializable;
import java.util.ArrayList;

public class Snake implements Constants, Serializable {
    private int health = 10;
    private float snakeHeadX = 240;
    private float snakeHeadY = 500;
    private float snakeSize = 25;
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

    public void update(StateBasedGame game){
        if(this.health <= 0){
            game.enterState(GAME_OVER_STATE);
        }
    }

    public void moveLeft() {
        if (snakeHeadX > 0) snakeHeadX -= .5;
    }

    public void moveRight() {
        if (snakeHeadX < GAME_WIDTH - snakeSize) snakeHeadX += .5;
    }

    public void moveUp() {
        if (snakeHeadY > 0) snakeHeadY -= .5;
    }

    public void moveDown() {
        if (snakeHeadY < WINDOW_HEIGHT - snakeSize) snakeHeadY += .5;
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

    public void setSnakeHeadX(float snakeHeadX) {
        this.snakeHeadX = snakeHeadX;
    }

    public float getSnakeHeadX() {
        return snakeHeadX;
    }

    public void setSnakeHeadY(float snakeHeadY) {
        this.snakeHeadY = snakeHeadY;
    }

    public float getSnakeHeadY() {
        return snakeHeadY;
    }

    public void setSnakeBody(ArrayList<SnakeBody> snakeBody) {
        this.snakeBody = snakeBody;
    }

    public ArrayList<SnakeBody> getSnakeBody() {
        return snakeBody;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
