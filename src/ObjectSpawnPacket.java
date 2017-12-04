import java.io.Serializable;
import java.util.ArrayList;

public class ObjectSpawnPacket implements Serializable{
//    private int id;
//    private int health;
//    private float xpos;
//    private float ypos;
//    private int type;
    private ArrayList<GameObject> gameObjects;

    public ObjectSpawnPacket(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

//    public ObjectSpawnPacket(int id, int type, int health, float xpos, float ypos) {
//        this.id = id;
//        this.type = type;
//        this.health = health;
//        this.xpos = xpos;
//        this.ypos = ypos;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public int getHealth() {
//        return health;
//    }
//
//    public float getXpos() {
//        return xpos;
//    }
//
//    public float getYpos() {
//        return ypos;
//    }

        public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
