
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.ArrayList;

public class PlayScreen extends BasicGameState{


    ArrayList<Obstacle> row = new ArrayList<>();
    RowGenerator gen = new RowGenerator(row);
    Snake snek = new Snake();
    public PlayScreen(int state){
        gen.start();
    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{

    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        g.drawString("Play State", 40, 50);
        snek.render(g);
            for(int i=0; i<row.size(); i++){
                row.get(i).render(g);
            }
//            for (Obstacle ob : row){
//                ob.render(g);
//            }


    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{
        snek.update(gameContainer);
        for(int i=0; i<row.size(); i++){
            if(row.get(i).collide(snek)) row.get(i).rowY+=.1;
        }

    }

    public int getID(){
        return 1;
    }
}
