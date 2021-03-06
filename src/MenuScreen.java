
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class MenuScreen extends BasicGameState implements Constants{
    String input = "";

    public MenuScreen(int state){

    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{
        gameContainer.setShowFPS(false);
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        g.drawString(input, 180, 100);
        g.drawString(GAME_NAME, 180, 210);
        g.drawLine(180, 240, 320, 240);
        g.drawString("Play", 180, 250);
//        g.drawRect(180, 250, 35, 20);
        g.drawString("Multiplayer", 180, 290);
//        g.drawRect(180, 290, 100, 20);
        g.drawString("Instruction", 180, 330);
//        g.drawRect(180, 330, 35, 20);
        g.drawString("High Score", 180, 370);
        g.drawString("Quit", 180, 410);
        g.drawLine(480,0,480,640);

        int mX = Mouse.getX();
        int mY = Mouse.getY();
//        input = "X: " + mX + "Y: " + mY;
        if((mX>180&&mX<215) && (mY>640-270 && mY<640-250)){ // play button
            if(Mouse.isButtonDown(0)){
                ((PlayScreen) game.getState(PLAY_STATE)).startGame();
                game.enterState(PLAY_STATE);
            }
        }
        if((mX>180&&mX<280) && (mY>640-310 && mY<640-290)){ // multi button
            if(Mouse.isButtonDown(0)){
                game.enterState(LOBBY_STATE);
            }
        }
        if((mX>180&&mX<280) && (mY>640-350 && mY<640-330)) { //instruction button
        	if(Mouse.isButtonDown(0)){
               game.enterState(INST_STATE);
            }
        }        
        if((mX>180&&mX<215) && (mY>640-430 && mY<640-410)){ // quit button
            if(Mouse.isButtonDown(0)){
                gameContainer.exit();
            }
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{
        gameContainer.getInput().clearKeyPressedRecord();
    }

    public int getID(){
        return MENU_STATE;
    }
}
