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
        g.drawString("Back to Menu", 220, 95);
        g.drawString("Quit", 220, 130);
        g.drawRect(220, 50, 100, 20);

        int mX = Mouse.getX();
        int mY = Mouse.getY();
        if((mX>220&&mX<320) && (mY>640-70 && mY<640-50)){ // play button
            if(Mouse.isButtonDown(0)){
                ((PlayScreen) game.getState(GameClient.play)).startGame();
                game.getState(GameClient.play).init(gameContainer, game);
                game.enterState(1);
            }
        }else if((mX>220&&mX<335) && (mY>640-105 && mY<640-90)) { //back to menu button
        	if(Mouse.isButtonDown(0)) {
        		 gameContainer.reinit();
        	}
        }else if((mX>220&&mX<320) && (mY>640-145 && mY<640-125)) {
        	if(Mouse.isButtonDown(0)) {
        		gameContainer.exit();
        	}
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta){

    }

    public int getID(){
        return GameClient.gameover;
    }
}
