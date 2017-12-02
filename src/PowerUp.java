import org.newdawn.slick.Graphics;
import java.util.Random;

public class PowerUp extends GameObject{
    public int health = 0;
    public float xpos = 0;
    public float ypos = 0;
    public int width = 15;
    public int height = 15;
    public String healthlabel = null;
    public PowerUp(int x){
        this.health = new Random().nextInt(4)+1;
        healthlabel = Integer.toString(this.health);
        this.xpos = x;
        this.ypos = 100;
    }

    @Override
    public void render(Graphics g){
        g.fillOval(xpos+30, ypos+30, width, height);
        g.drawString(healthlabel, xpos+45, ypos+30);
    }

    @Override
    public boolean collide(Snake snake){
        if((this.ypos-30 < snake.snakeHeadY-25) && (this.ypos+30 > snake.snakeHeadY-25) && (this.xpos-30 <snake.snakeHeadX-25) && (this.xpos+30 >snake.snakeHeadX-25) && (this.health != 0)){
            snake.health+=this.health;
            while(this.health != 0){
                this.health--;
            }

            if(this.health == 0){
                this.width=0;
                this.height=0;
                healthlabel = "";
            }

            return false;
        }
        return true;
    }

    public void moveY(){
        this.ypos+=.1;
    }

    public float getY(){
        return this.ypos;
    }
}
