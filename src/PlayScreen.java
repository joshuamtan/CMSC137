
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import java.util.ArrayList;

public class PlayScreen extends BasicGameState{

    ArrayList<GameObject> row = null;
    RowGenerator gen = null;
    Snake snek = null;
    int score = 0;
    String scoreString = null;
    TextField messageField;
    TextField messages;
    int textboxHeight = 40;
    int textboxWidth = GameClient.windowWidth - GameClient.gameWidth;
    ChatClient client = null;
    ArrayList<String> messagesList;

    public PlayScreen(int state){
    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{
        snek = new Snake();
        row = new ArrayList<>();
        gen = new RowGenerator(row);
        score = 0;
        messageField = new TextField(gameContainer, gameContainer.getDefaultFont(),GameClient.gameWidth,GameClient.gameHeight - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gameContainer, gameContainer.getDefaultFont(),GameClient.gameWidth,0,textboxWidth,GameClient.gameHeight - messageField.getHeight());
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
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException{
        snek.update(gameContainer, game);
        for(int i=0; i<row.size(); i++){
            if(row.get(i).collide(snek)) {
                row.get(i).moveY();
                score += 1;
            }
        }
        messagesList = client.getListener().getMessages();
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)) {
            if (client != null) {
                String message = messageField.getText();
                client.sendMessage(message);
                messagesList.add("me: "+ message);
                messageField.setText("");
            }
        }
        String messagesString = "";
        for( String m : messagesList) {
            messagesString = messagesString + m + "\n";
        }

        messages.setText(messagesString);
    }

    public void addChatClient(ChatClient client) {
        this.client = client;
    }

    public void startGame() { gen.start(); }

    public int getID(){
        return GameClient.play;
    }
}
