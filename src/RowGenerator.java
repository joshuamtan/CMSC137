import java.util.ArrayList;
import java.util.Random;

public class RowGenerator extends Thread implements Constants {
    ArrayList<GameObject> gameObjects;
    ArrayList<Player> players;
    int id = 0;

    int maxHealth = 1;
    public RowGenerator(ArrayList<Player> players){
        this.gameObjects = new ArrayList<>();
        this.players = players;
    }

    public void updateMaxHealth() {
        for (Player p: players) {
            maxHealth += p.getSnake().getHealth();
        }
        if (players.size() > 2) maxHealth = (int)(maxHealth/players.size());
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void run(){
        while (true){
            updateMaxHealth();
            for(int i = 5; i<480; i+=80){
                int decision = new Random().nextInt() % 10;
                if (decision < 4) {
                    id+=1;
                    gameObjects.add(new Obstacle(id, i, maxHealth));
                }else if (decision > 4 && decision < 8) {
                    gameObjects.add(new PowerUp(id, i));
                    id+=1;
                }
            }

            GameClient.getGameServer().objectSpawn(gameObjects);

            try{
                this.sleep(3000);
            } catch (Exception e){
            }
        }
    }
}
