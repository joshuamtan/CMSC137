import org.newdawn.slick.Graphics;
import java.util.Random;

public class PowerUp extends GameObject{
    private int id;
    private int health = 0;
    private float xpos;
    private float ypos;
    private int width = 15;
    private int height = 15;
    private String healthlabel = null;

    public PowerUp(int id, int x){
        this.id = id;
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
        if((this.ypos-30 < snake.getSnakeHeadY()-25) && (this.ypos+30 > snake.getSnakeHeadY()-25) && (this.xpos-30 <snake.getSnakeHeadX()-25) && (this.xpos+30 >snake.getSnakeHeadX()-25) && (this.health != 0)){
            snake.setHealth(snake.getHealth()+this.health);
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
