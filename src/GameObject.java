import org.newdawn.slick.Graphics;

public abstract class GameObject {
    public int health;
    public float xpos;
    public float ypos;

    public GameObject(){}

    public abstract void render(Graphics g);

    public abstract boolean collide(Snake snek);

    public abstract void moveY();
}
