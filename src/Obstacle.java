import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.ArrayList;

public class Obstacle extends GameObject{
    private int health = 2;
    public float xpos;
    public float ypos = 100;
    public int width = 70;
    public int height = 70;
    public String healthLabel = Integer.toString(health);

    public Obstacle(float rowX){
        this.xpos = rowX;
        health = (int) (Math.random()*20) + 1;
        healthLabel = Integer.toString(health);
    }

    @Override
    public void render(Graphics g){
        g.drawRect(xpos, ypos, width, height);
        g.drawString(healthLabel, xpos+30, ypos+30);
    }

    @Override
    public boolean collide(Snake snake){
        if((this.ypos-40 < snake.snakeY-25) && (this.ypos+40 > snake.snakeY-25) && (this.xpos-40 <snake.snakeX-25) && (this.xpos+40 >snake.snakeX-25) && (this.health != 0)){
            snake.health-=this.health;
            while(this.health != 0){
                this.health--;
            }

            if(this.health == 0){
                this.width=0;
                this.height=0;
                this.healthLabel = "";
            }

            return false;
        }
        return true;
    }

    @Override
    public void moveY(){
        this.ypos+=.1;
    }
}
