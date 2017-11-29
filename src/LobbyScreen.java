import com.sun.deploy.util.StringUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class LobbyScreen extends BasicGameState {
    TextField host;
    StateBasedGame game;

    public LobbyScreen(int state, StateBasedGame game){
        this.game = game;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setShowFPS(false);
        host = new TextField(gameContainer, gameContainer.getDefaultFont(),190,360,200,20);
        host.setBackgroundColor(Color.white);
        host.setTextColor(Color.black);
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Snake vs Blocks", 180, 210);
        g.drawLine(180, 240, 320, 240);
        g.drawString("Type host address to join", 180, 250);
        g.drawString("If no host address is provided,",180, 280);
        g.drawString("it will create a new game,",180, 310);
        host.render(gameContainer, g);
        g.drawString("Start", 220, 400);

        // separator
        g.drawLine(480,0,480,640);
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int i) throws SlickException {
        // getting input from the user
        Input in = gameContainer.getInput();

        int mX = Mouse.getX();
        int mY = Mouse.getY();

        if((mX>220 && mX<260) && (mY>640-410 && mY<640-400)){ // start button
            if(Mouse.isButtonDown(0)){
                String hostAddress = host.getText();
                if (hostAddress.length() ==  0) {// start server
                    try {
                        (new ChatServer()).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    hostAddress = "localhost";
                }
                ChatClient client = new ChatClient(hostAddress);
                client.start();

                ((PlayScreen) game.getState(GameClient.play)).addChatClient(client);
                ((PlayScreen) game.getState(GameClient.play)).startGame();
                game.enterState(GameClient.play); // enter play state
            }
        }
    }
    public int getID() { return GameClient.lobby;    }
}
