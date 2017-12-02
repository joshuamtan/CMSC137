
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import java.util.ArrayList;

public class PlayScreen extends BasicGameState implements Constants {
    ArrayList<GameObject> row = null;
    RowGenerator gen = null;
    Snake snek = null;
    int score = 0;
    String scoreString = null;
    TextField messageField;
    TextField messages;
    int textboxHeight = 40;
    int textboxWidth = WINDOW_WIDTH - GAME_WIDTH;
    ChatClient client = null;
    ArrayList<String> messagesList;

    public PlayScreen(int state){
    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{
        snek = new Snake();
        row = new ArrayList<>();
        gen = new RowGenerator(row);
        gameContainer.setAlwaysRender(true);
        score = 0;
        messageField = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,WINDOW_HEIGHT - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,0,textboxWidth,WINDOW_HEIGHT - messageField.getHeight());
        messageField.setBackgroundColor(Color.white);
        messageField.setTextColor(Color.black);
        messages.setBackgroundColor(Color.white);
        messages.setTextColor(Color.black);
        messages.setAcceptingInput(false);
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        scoreString = Integer.toString(score);
        g.drawString("Score: " + scoreString, 40, 50);
        snek.render(g);
        for(int i=0; i<row.size(); i++){
            row.get(i).render(g);
        }
        messageField.render(gameContainer, g);
        messages.render(gameContainer, g);
        g.setColor(Color.black);
        g.drawLine(GAME_WIDTH, messages.getHeight(), WINDOW_WIDTH, messages.getHeight());
        g.setColor(Color.white);
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{
        snek.update(gameContainer, game);
        for(int i=0; i<row.size(); i++){
            if(row.get(i).collide(snek)) {
                row.get(i).moveY();
                score += 1;
            }
            if(row.get(i).getY() > 640){
                row.remove(i);
            }
        }
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

    public void startGame() { gen.start(); }

    public int getID(){
        return PLAY_STATE;
    }
}
