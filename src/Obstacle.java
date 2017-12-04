import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Obstacle extends GameObject{
    private int health = 2;
    private int id;
    private float xpos;
    private float ypos;
    private int width = 70;
    private int height = 70;
    private String healthLabel = Integer.toString(health);

    public Obstacle(int id, float rowX, int maxHealth){
        this.id = id;
        this.xpos = rowX;
        this.ypos = 100;
        health = (int) (Math.random()*maxHealth) + 1;
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
        if((this.ypos-20 < snake.getSnakeHeadY()+25) && (this.ypos+20 > snake.getSnakeHeadY()-25) && (this.xpos-40 <snake.getSnakeHeadX()-25) && (this.xpos+40 >snake.getSnakeHeadX()-25) && (this.health != 0)){
            snake.setHealth(snake.getHealth()-this.health);
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

    public void moveY(){
        this.ypos+=0.1;
    }

    public float getYpos(){
        return this.ypos;
    }

    public float getXpos() {
        return this.xpos;
    }

    public int getHealth() {
        return this.health;
    }

    public int getId() {
        return id;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }
}
