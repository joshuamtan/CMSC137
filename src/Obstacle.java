import org.newdawn.slick.Graphics;

import java.awt.*;

public class Obstacle {
    private int health;
    public float rowX;
    public float rowY = 100;
    public Obstacle(float rowX){
        this.rowX = rowX;
    }

    public void render(Graphics g){
        g.drawRect(rowX, rowY, 30, 30);
    }
//    public void update(){
//
//    }
}
