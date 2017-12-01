import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class WaitingScreen extends BasicGameState implements Constants {
    TextField messageField;
    TextField messages;
    int textboxHeight = 40;
    int textboxWidth = WINDOW_WIDTH - GAME_WIDTH;
    ChatClient client = null;
    ArrayList<String> messagesList;

    public WaitingScreen(int state) {  }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        messageField = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,WINDOW_HEIGHT - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,0,textboxWidth,WINDOW_HEIGHT - messageField.getHeight());
        messageField.setBackgroundColor(Color.white);
        messageField.setTextColor(Color.black);
        messages.setAcceptingInput(false);
    }


    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString(GAME_NAME, 180, 210);
        g.drawLine(180, 240, 320, 240);
        g.drawString("Waiting for other players...", 180, 250);

        messageField.render(gameContainer, g);
        messages.render(gameContainer, g);
        g.setColor(Color.black);
        g.drawLine(GAME_WIDTH, messages.getHeight(), WINDOW_WIDTH, messages.getHeight());
        g.setColor(Color.white);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (client != null) {
            messagesList = client.getListener().getMessages();
            String messagesString = "";
            for (String m : messagesList) {
                messagesString = messagesString + m + "\n";
            }
            messages.setText(messagesString);

            if (gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)) {
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

    public int getID() { return WAITING_STATE; }
}
