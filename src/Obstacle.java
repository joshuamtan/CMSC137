import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.ArrayList;

public class Obstacle {
    private int health = 2;
    public float rowX;
    public float rowY = 100;
    public int width = 70;
    public int height = 70;
    public String healthLabel = Integer.toString(health);

    public Obstacle(float rowX){
        this.rowX = rowX;
        health = (int) (Math.random()*20) + 1;
        healthLabel = Integer.toString(health);
    }

    public void render(Graphics g){
        g.drawRect(rowX, rowY, width, height);
        g.drawString(healthLabel, rowX+30, rowY+30);
    }
//    public void update(){
//
//    }

    public boolean collide(Snake snake){
        if((this.rowY-40 < snake.snakeY-25) && (this.rowY+40 > snake.snakeY-25) && (this.rowX-40 <snake.snakeX-25) && (this.rowX+40 >snake.snakeX-25) && (this.health != 0)){
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


}
