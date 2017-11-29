import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

public class RowGenerator extends Thread {
    ArrayList<GameObject> obstacles;

    public RowGenerator(ArrayList<GameObject> obstacles){
        this.obstacles = obstacles;
    }

    public void run(){
        while (true){
            for(int i = 5; i<480; i+=80){
                int decision = new Random().nextInt() % 10;
                if (decision < 4) {
                    obstacles.add(new Obstacle(i));
                }else if (decision > 4 && decision < 8) {
                    obstacles.add(new PowerUp(i));
                }
            }

            try{
                this.sleep(3000);
            }catch (Exception e){
            }
        }
    }
}
