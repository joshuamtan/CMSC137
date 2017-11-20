
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class MenuScreen extends BasicGameState{
    public String input = "X";


    public MenuScreen(int state){

    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        g.drawString(input, 220, 10);
        g.drawString("Play", 220, 50);
        g.drawRect(210, 40, 60, 40);

        int mX = Mouse.getX();
        int mY = Mouse.getY();
        input = "X: " + mX + " Y: " + mY;
        if((mX>210&&mX<270) && (mY>640-80 && mY<640-40)){
            if(Mouse.isButtonDown(0)){
                game.enterState(1);
            }
        }

    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{

    }

    public int getID(){
        return 0;
    }
}
