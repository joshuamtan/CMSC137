
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import java.util.ArrayList;

public class PlayScreen extends BasicGameState implements Constants {
    static ArrayList<Player> players;
    static Player currentPlayer;
    static ArrayList<GameObject> row = new ArrayList<>();
    String scoreString = null;
    TextField messageField;
    TextField messages;
    int textboxHeight = 40;
    int textboxWidth = WINDOW_WIDTH - GAME_WIDTH;
    ChatClient client = null;
    ArrayList<String> messagesList;
    int seconds, mov, j = 0;
    static int gameState = IN_PROGRESS;

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

    public static void updatePlayer(String name, int score, Snake snake) {
        for (Player p: PlayScreen.players) {
            if (p.getName().equals(name) && !(p.getName().equals(GameClient.getName()))) {
                p.setScore(score);
                p.setSnake(snake);
            }
        }
    }

    public static void updateSnake(String name, float snakeHeadX, float snakeHeadY, ArrayList<SnakeBody> snakeBody, int health) {
        for (Player p: PlayScreen.players) {
            if (p.getName().equals(name) && !(p.getName().equals(GameClient.getName()))) {
                Snake snek = p.getSnake();
                snek.setSnakeHeadX(snakeHeadX);
                snek.setSnakeHeadY(snakeHeadY);
                snek.setSnakeBody(snakeBody);
                snek.setHealth(health);
            }
        }
    }

    public static void handleGameObjectSpawn(ArrayList<GameObject> gameObjects) {
        for (GameObject go : gameObjects) {
            if (!row.contains(go)) {
                row.add(go);
            }
        }
    }

//    public static void handleGameObjectSpawn(int id, int type, int health, float xpos, float ypos) {
//        if (type == POWER_UP) row.add(new PowerUp(id, (int) xpos));
//        else row.add(new Obstacle(id, xpos, health));
//    }

    public static void handleGameObjectUpdate(int id, int health, float xpos, float ypos) {
        for (int i=0; i<row.size(); i++) {
            GameObject go = row.get(i);
            if (go.getId() == id) {
                go.setXpos(xpos);
                go.setYpos(ypos);
                break;
            }
        }
    }

    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException{
        gameContainer.setAlwaysRender(true);
        gameContainer.setSmoothDeltas(true);
        messageField = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,WINDOW_HEIGHT - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,0,textboxWidth,WINDOW_HEIGHT - messageField.getHeight());
        messageField.setBackgroundColor(Color.white);
        messageField.setTextColor(Color.black);
        messages.setAcceptingInput(false);
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
        scoreString = Integer.toString(currentPlayer.getScore());
        g.drawString("Score: " + scoreString, 40, 50);
        // render all snake of players
        for (Player p: players) {
            p.getSnake().render(g);
        }

        // render obstacles and power ups
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
        Snake snek = currentPlayer.getSnake();
        if (mov>1){
            if(j == snek.snakeBody.size()){
                j = 0;
            }
            snek.moveBody(j);
            j++;
            mov=0;
        }

        // update other player's snake
        for (Player p: players) {
            p.getSnake().update(game);
        }

        // snake movement
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_LEFT)){
            snek.moveLeft();
        }
        if (in.isKeyDown(Input.KEY_RIGHT)){
            snek.moveRight();
        }
        if (in.isKeyDown(Input.KEY_UP)){
            snek.moveUp();
        }
        if (in.isKeyDown(Input.KEY_DOWN)){
            snek.moveDown();
        }
        if (seconds % 1000 == 0 && seconds > 0){
            int newScore = currentPlayer.getScore() + 10;
            currentPlayer.setScore(newScore);
            NetworkHelper.clientSend(new Packet("PLAYER_UPDATE", new PlayerUpdatePacket(GameClient.getName(),
                    PlayScreen.currentPlayer.getScore(), PlayScreen.currentPlayer.getSnake())), NetworkHelper.getHost());
        }
        NetworkHelper.clientSend(new Packet("SNAKE_UPDATE", new SnakeUpdatePacket(GameClient.getName(),
                snek.getSnakeHeadX(), snek.getSnakeHeadY(), snek.getSnakeBody(), snek.getHealth())),
                NetworkHelper.getHost());

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

    public int getID(){
        return PLAY_STATE;
    }
}
