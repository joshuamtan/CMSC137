
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import java.util.ArrayList;

public class PlayScreen extends BasicGameState implements Constants {
    static ArrayList<Player> players;
    static Player currentPlayer;
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
    int seconds, mov, j = 0;

    public PlayScreen(int state){
    }

    public static void initPlayers(ArrayList<Player> players) {
        PlayScreen.players = players;
        for (Player p: players) {
            if (p.getName().equals(GameClient.getName())) {
                currentPlayer = p;
            }
        }
    }

    public void init(GameContainer gc, StateBasedGame game) throws SlickException{
        snek = new Snake();
        row = new ArrayList<>();
        gen = new RowGenerator(row, snek);
        gc.setAlwaysRender(true);
        gc.setSmoothDeltas(true);
        messageField = new TextField(gc, gc.getDefaultFont(),GAME_WIDTH,WINDOW_HEIGHT - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gc, gc.getDefaultFont(),GAME_WIDTH,0,textboxWidth,WINDOW_HEIGHT - messageField.getHeight());;
        messageField.setBackgroundColor(Color.white);
        messageField.setTextColor(Color.black);
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

        seconds += delta;
        mov += delta;
        snek.update(gameContainer, game);
        for(int i=0; i<row.size(); i++){
            if(row.get(i).collide(snek)) {
                row.get(i).moveY();

            }
            if(row.get(i).getY() > 640){
                row.remove(i);
            }
        }

        if (mov>1){
            if(j == snek.snakeBody.size()){
                j = 0;
            }
            snek.moveBody(j);
            j++;
            mov=0;
        }

        if(seconds>1000){
            score+=10;
            seconds=0;

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
