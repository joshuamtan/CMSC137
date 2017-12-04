
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameClient extends StateBasedGame implements Constants{
    private static GameServer gameServer;
    private static String name;

    static Thread clientReceiver;
    static Thread serverReceiver;

    public GameClient(){
        super(GAME_NAME);
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException{
        this.addState(new MenuScreen(MENU_STATE));  //add menu state to game
        this.addState(new GameOverScreen(GAME_OVER_STATE));  //add game over state to game
        this.addState(new PlayScreen(PLAY_STATE));  //add play state to game
        this.addState(new LobbyScreen(LOBBY_STATE, this));  //add lobby state to game
        this.addState(new WaitingScreen(WAITING_STATE));  //add waiting state to game
        this.addState(new InstructionScreen(INST_STATE)); //add instruction state to game
        this.enterState(MENU_STATE);  //show initial screen menu
    }

    public static void main(String[] args){
        AppGameContainer window = null;
        try{    //initialize game container
            window = new AppGameContainer(new GameClient());
            window.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
            window.start();
        }catch (SlickException e){
            e.printStackTrace();
        }
    }

    public static void createServer() {
        serverReceiver = NetworkHelper.createServerReceiver();
        serverReceiver.start();
        gameServer = new GameServer();
    }

    public static void createClient() {
        clientReceiver = NetworkHelper.createClientReceiver();
        clientReceiver.start();
    }

    public static void endServer() {
        serverReceiver.interrupt();
    }

    public static void endClient() {
        clientReceiver.interrupt();
    }

    public static void setName(String name) {
        GameClient.name = name;
    }

    public static String getName() {
        return GameClient.name;
    }

    public static GameServer getGameServer() {
        return gameServer;
    }

}