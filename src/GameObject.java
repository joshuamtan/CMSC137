import org.newdawn.slick.Graphics;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private int id;
    private int health;
    private float xpos;
    private float ypos;

    public GameObject(){}

    public abstract void render(Graphics g);

    public abstract boolean collide(Snake snek);

    public abstract void moveY();

    public abstract float getYpos();

    public abstract float getXpos();

    public abstract int getHealth();

    public abstract int getId();

    public abstract void setXpos(float xpos);

    public abstract void setYpos(float ypos);
}
