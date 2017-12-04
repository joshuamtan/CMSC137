import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class WaitingScreen extends BasicGameState implements Constants {
    TextField messageField;
    TextField messages;
    TextField notifs;
    int textboxHeight = 40;
    int textboxWidth = WINDOW_WIDTH - GAME_WIDTH;
    ChatClient client = null;
    ArrayList<String> messagesList;
    static int gameState = WAITING_FOR_PLAYERS;
    static ArrayList<Player> players;

    public WaitingScreen(int state) {  }

    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        gc.setAlwaysRender(true);
        gc.setSmoothDeltas(true);
        messageField = new TextField(gc, gc.getDefaultFont(),GAME_WIDTH,WINDOW_HEIGHT - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gc, gc.getDefaultFont(),GAME_WIDTH,0,textboxWidth,WINDOW_HEIGHT - messageField.getHeight());
        notifs = new TextField(gc, gc.getDefaultFont(),180,250,300,textboxHeight);
        messageField.setBackgroundColor(Color.white);
        messageField.setTextColor(Color.black);
        messages.setAcceptingInput(false);
        notifs.setAcceptingInput(false);
        notifs.setBorderColor(Color.black);
        notifs.setBackgroundColor(Color.black);
        notifs.setTextColor(Color.white);
    }

    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString(GAME_NAME, 180, 210);
        g.drawLine(180, 240, 320, 240);
        if (NetworkHelper.isServer()) {
            notifs.render(gc, g);
            g.drawString("START", 180, 300);
        }
        messageField.render(gc, g);
        messages.render(gc, g);
        g.setColor(Color.black);
        g.drawLine(GAME_WIDTH, messages.getHeight(), WINDOW_WIDTH, messages.getHeight());
        g.setColor(Color.white);
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        int mX = Mouse.getX();
        int mY = Mouse.getY();

        if (NetworkHelper.isServer()) {
            if (WaitingScreen.gameState == GAME_READY) {
                // render start button
                notifs.setText("Game is ready.");
            } else {
                // remove start button
                notifs.setText("Waiting for other players...");
            }
        }

        if (WaitingScreen.gameState == GAME_START || WaitingScreen.gameState == GAME_READY) {
            PlayScreen.initPlayers(WaitingScreen.players);
            if (WaitingScreen.gameState == GAME_START) game.enterState(PLAY_STATE);
        }

        if (NetworkHelper.isServer() && (mX>180 && mX<220) && (mY>(WINDOW_HEIGHT-320) && mY<(WINDOW_HEIGHT-300))) {
            if(Mouse.isButtonDown(0)){
                GameClient.getGameServer().startGame();
            }
        }

        if (client != null) {
            messagesList = client.getListener().getMessages();
            String messagesString = "";
            for (String m : messagesList) {
                messagesString = messagesString + m + "\n";
            }
            messages.setText(messagesString);

            if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
                if (messageField.getText().length() > 0) {
                    String message = messageField.getText();
                    client.sendMessage(message);
                    messagesList.add("me: " + message);
                    messageField.setText("");
                }
            }
            else {
                messageField.setFocus(true);
            }
        }
    }

    public void addChatClient(ChatClient client) {
        this.client = client;
    }

    public static void setPlayers(ArrayList<Player> players) {
        WaitingScreen.players = players;
    }

    public static void setGameState(int gameState) {
       WaitingScreen.gameState = gameState;
    }

    public int getID() { return WAITING_STATE; }
}
