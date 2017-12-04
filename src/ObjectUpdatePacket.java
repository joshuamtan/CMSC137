import java.io.Serializable;

public class ObjectUpdatePacket implements Serializable {
    private int id;
    private int health;
    private float xpos;
    private float ypos;

    public ObjectUpdatePacket(int id, int health, float xpos, float ypos) {
        this.id = id;
        this.health = health;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public float getXpos() {
        return xpos;
    }

    public float getYpos() {
        return ypos;
    }
}
