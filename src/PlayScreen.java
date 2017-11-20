
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.ArrayList;

public class PlayScreen extends BasicGameState{

    float snakeX = 240;
    float snakeY = 500;
    ArrayList<Obstacle> row = new ArrayList<>();
    RowGenerator gen = new RowGenerator(row);
    public PlayScreen(int state){
        gen.start();
    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{

    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        g.drawString("Play State", 40, 50);
        g.fillOval(snakeX, snakeY, 25, 25);
        for (Obstacle ob : row){
            ob.render(g);
        }

    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_LEFT)){snakeX -= .5; }
        if (in.isKeyDown(Input.KEY_RIGHT)){snakeX += .5; }
        for (Obstacle obs : row){
            obs.rowY+=.1;
        }
    }

    public int getID(){
        return 1;
    }
}
