
import javax.swing.*;
import java.awt.*;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.*;

public class GameClient extends StateBasedGame{
    public static final String gamename = "Snake vs Blocks";
    public static final int menu = 0;   //menu state identifier
    public static final int play = 1;   //play state identifier
    public static final int gameover = 2;   //play state identifier
    public static final int lobby = 3;   //play state iden
    public static final int gameWidth = 480;
    public static final int gameHeight = 640;

    public GameClient(String gamename){
        super(gamename);
        this.addState(new MenuScreen(menu));  //add menu state to game
        this.addState(new PlayScreen(play));  //add menu state to game
        this.addState(new GameOverScreen(gameover));  //add menu state to game
        this.addState(new LobbyScreen(lobby));  //add menu state to game
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException{
        this.getState(menu).init(gameContainer, this); //initialize menu state
        this.getState(play).init(gameContainer, this); //initialize play state
        this.enterState(menu);  //show initial screen menu
    }

    public static void main(String[] args){
        AppGameContainer window = null;
        try{    //initialize game container
            window = new AppGameContainer(new GameClient(gamename));
            window.setDisplayMode(gameWidth, gameHeight, false);
            window.start();
        }catch (SlickException e){
            e.printStackTrace();
        }
    }

}