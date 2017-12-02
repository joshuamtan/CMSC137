import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Obstacle extends GameObject{
    private int health = 2;
    public float xpos;
    public float ypos = 100;
    public int width = 70;
    public int height = 70;
    public String healthLabel = Integer.toString(health);

    public Obstacle(float rowX, Snake snek){
        this.xpos = rowX;
        health = (int) (Math.random()*snek.health) + 1;
        healthLabel = Integer.toString(health);
    }

    @Override
    public void render(Graphics g){
        if(this.health <= 10){
            g.setColor(Color.green);
        }else if(this.health <= 20 && this.health > 10){
            g.setColor(Color.yellow);
        }else if(this.health <= 30 && this.health > 20){
            g.setColor(Color.orange);
        }else{
            g.setColor(Color.red);
        }
        g.drawRect(xpos, ypos, width, height);
        g.fillRect(xpos, ypos, width, height);
        g.setColor(Color.black);
        g.drawString(healthLabel, xpos+30, ypos+30);
        g.setColor(Color.white);

    }

    @Override
    public boolean collide(Snake snake){
        if((this.ypos-20 < snake.snakeHeadY+25) && (this.ypos+20 > snake.snakeHeadY-25) && (this.xpos-40 <snake.snakeHeadX-25) && (this.xpos+40 >snake.snakeHeadX-25) && (this.health != 0)){
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

    public float getY(){
        return this.ypos;
    }
}
