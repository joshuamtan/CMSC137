import com.sun.deploy.util.StringUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class LobbyScreen extends BasicGameState {
    String hostAddress = "";

    public LobbyScreen(int state){

    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setShowFPS(false);
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Snake vs Blocks", 180, 210);
        g.drawLine(180, 240, 320, 240);
        g.drawString("Type host address to join", 180, 250);
        g.drawString("If no host address is provided,",180, 280);
        g.drawString("it will create a new game,",180, 310);
        g.drawString(hostAddress, 200, 360);
        g.drawRect(190, 360, 200, 20);
        g.drawString("Start", 220, 400);

        // separator
        g.drawLine(480,0,480,640);
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int i) throws SlickException {
        // getting input from the user
        Input in = gameContainer.getInput();
        if (in.isKeyPressed(Input.KEY_0)) {
            if (hostAddress.length() < 15) hostAddress+= 0;
        }
        if (in.isKeyPressed(Input.KEY_1)) {
            if (hostAddress.length() < 15) hostAddress+= 1;
        }
        if (in.isKeyPressed(Input.KEY_2)) {
            if (hostAddress.length() < 15) hostAddress+= 2;
        }
        if (in.isKeyPressed(Input.KEY_3)) {
            if (hostAddress.length() < 15) hostAddress+= 3;
        }
        if (in.isKeyPressed(Input.KEY_4)) {
            if (hostAddress.length() < 15) hostAddress+= 4;
        }
        if (in.isKeyPressed(Input.KEY_5)) {
            if (hostAddress.length() < 15) hostAddress+= 5;
        }
        if (in.isKeyPressed(Input.KEY_6)) {
            if (hostAddress.length() < 15) hostAddress+= 6;
        }
        if (in.isKeyPressed(Input.KEY_7)) {
            if (hostAddress.length() < 15) hostAddress+= 7;
        }
        if (in.isKeyPressed(Input.KEY_8)) {
            if (hostAddress.length() < 15) hostAddress+= 8;
        }
        if (in.isKeyPressed(Input.KEY_9)) {
            if (hostAddress.length() < 15) hostAddress+= 9;
        }
        if (in.isKeyPressed(Input.KEY_PERIOD)) {
            if (hostAddress.length() < 15) hostAddress+= ".";
        }
        if (in.isKeyPressed(Input.KEY_BACK)) {
            if (hostAddress.length() > 0) hostAddress = hostAddress.substring(0,hostAddress.length()-1);
        }

        int mX = Mouse.getX();
        int mY = Mouse.getY();

        if((mX>220 && mX<260) && (mY>640-410 && mY<640-400)){ // start button
            if(Mouse.isButtonDown(0)){
                if (hostAddress.length() > 0) {// start client only
                    (new ChatClient(hostAddress, gameContainer.getInput())).start();
                }
                else { // start server and client
                    try {
                        (new ChatServer()).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    (new ChatClient("localhost", gameContainer.getInput())).start();
                }
                game.enterState(GameClient.play); // enter play state
            }
        }
    }

    public int getID() { return GameClient.lobby;    }
}
