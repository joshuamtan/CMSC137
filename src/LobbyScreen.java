import com.sun.deploy.util.StringUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class LobbyScreen extends BasicGameState implements Constants {
    TextField host;
    StateBasedGame game;
    final int RECT_HEIGHT = 20;
    final int RECT_WIDTH = 150;

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
        g.drawString(GAME_NAME, 180, 210);
        g.drawLine(180, 240, 320, 240);
        g.drawString("Create a new game", 180, 270);
        g.drawString("Create Game", 220, 300);
        g.drawRect(210,300, RECT_WIDTH, RECT_HEIGHT);
        g.drawString("or type host address to join", 180, 330);
        host.render(gameContainer, g);
        g.drawString("Join Game", 220, 390);
        g.drawRect(210,390, RECT_WIDTH, RECT_HEIGHT);

        g.drawLine(GAME_WIDTH,0,GAME_WIDTH,WINDOW_HEIGHT);  // separator
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int i) throws SlickException {
        Input in = gameContainer.getInput();    // getting input from the user

        int mX = Mouse.getX();
        int mY = Mouse.getY();

        if ((mX>210 && mX<360) && (mY>(WINDOW_HEIGHT-300-RECT_HEIGHT) && mY<(WINDOW_HEIGHT-300))) { // create button
            if(Mouse.isButtonDown(0)){
                String hostAddress = "localhost";
                // start the chat server
                try {
                    (new ChatServer()).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // start chat
                ChatClient client = new ChatClient(hostAddress);
                client.start();
                ((WaitingScreen) game.getState(WAITING_STATE)).addChatClient(client);

                GameClient.createServer();
                GameClient.createClient();
                NetworkHelper.connect(hostAddress, "Client");

                game.enterState(WAITING_STATE); // enter waiting state

            }
        }

        if((mX>210 && mX<360) && (mY>(WINDOW_HEIGHT-390-RECT_HEIGHT) && mY<(WINDOW_HEIGHT-390))){ // join button
            if(Mouse.isButtonDown(0)){
                String hostAddress = host.getText();
                // start chat
                ChatClient client = new ChatClient(hostAddress);
                client.start();

                ((WaitingScreen) game.getState(WAITING_STATE)).addChatClient(client);

                GameClient.createClient();
                NetworkHelper.connect(hostAddress, "Client");

                game.enterState(WAITING_STATE); // enter waiting state
//                ((GameOverScreen) game.getState(GAME_OVER_STATE)).addChatClient(client);
//                ((PlayScreen) game.getState(PLAY_STATE)).startGame();
            }
        }
    }
    public int getID() { return LOBBY_STATE; }
}
