import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class RowGenerator extends Thread {
    ArrayList<Obstacle> obstacles;
    int rowNum = 0;

    public RowGenerator(ArrayList<Obstacle> obstacles){
        this.obstacles = obstacles;
    }

    public void run(){
        while (true){
            for(int i = 5; i<470; i+=80){
                Obstacle obs = new Obstacle(i);
                obstacles.add(obs);
            }

            try{
                this.sleep(5000);
            }catch (Exception e){
            }
        }
    }
}