
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.ArrayList;

public class PlayScreen extends BasicGameState{

    ArrayList<GameObject> row = null;
    RowGenerator gen = null;
    Snake snek = null;
    int score = 0;
    String scoreString = null;
    public PlayScreen(int state){
    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{
        snek = new Snake();
        row = new ArrayList<>();
        gen = new RowGenerator(row);
        score = 0;
        gen.start();
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        scoreString = Integer.toString(score);
        g.drawString("Score: " + scoreString, 40, 50);
        snek.render(g);
        for(int i=0; i<row.size(); i++) {
            row.get(i).render(g);
        }

    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{
        snek.update(gameContainer, game);
        for(int i=0; i<row.size(); i++){
            if(row.get(i).collide(snek)) {
                row.get(i).moveY();
                score += 1;
            }
        }
    }

    public int getID(){
        return 1;
    }
}
