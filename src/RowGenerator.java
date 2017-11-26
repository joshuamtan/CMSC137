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
                if (new Random().nextBoolean() == true) {
                    Obstacle obs = new Obstacle(i);
                    obstacles.add(obs);
                }else{
                    if(new Random().nextBoolean() == true){
                        PowerUp pow = new PowerUp(i);
                        obstacles.add(pow);
                    }

                }
            }

            try{
                this.sleep(3000);
            }catch (Exception e){
            }
        }
    }
}
