import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverScreen extends BasicGameState
{

    public GameOverScreen(int state){

    }

    public void init(GameContainer gameContainer, StateBasedGame game){

    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        g.drawString("Game Over", 220, 10);
        g.drawString("Play Again", 220, 50);
        g.drawRect(220, 50, 100, 20);
        g.drawLine(480,0,480,640);

        int mX = Mouse.getX();
        int mY = Mouse.getY();
        if((mX>220&&mX<320) && (mY>640-70 && mY<640-50)){ // play button
            if(Mouse.isButtonDown(0)){
                game.getState(1).init(gameContainer, game);
                game.enterState(1);
            }
        }

    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta){

    }

    public int getID(){
        return GameClient.gameover;
    }
}
