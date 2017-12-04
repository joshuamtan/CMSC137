import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class GameOverScreen extends BasicGameState implements Constants{
    TextField messageField;
    TextField messages;
    int textboxHeight = 40;
    int textboxWidth = WINDOW_WIDTH - GAME_WIDTH;
    ChatClient client = null;
    ArrayList<String> messagesList;

    public GameOverScreen(int state){

    }

    public void init(GameContainer gameContainer, StateBasedGame game){
        messageField = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,WINDOW_HEIGHT - textboxHeight,textboxWidth,textboxHeight);
        messages = new TextField(gameContainer, gameContainer.getDefaultFont(),GAME_WIDTH,0,textboxWidth,WINDOW_HEIGHT - messageField.getHeight());
        messageField.setBackgroundColor(Color.white);
        messageField.setTextColor(Color.black);
        messages.setBackgroundColor(Color.white);
        messages.setTextColor(Color.black);
        messages.setAcceptingInput(false);
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
    	g.drawString("Game Over", 220, 10);
        g.drawString("Play Again", 220, 50);
        g.drawString("Back to Menu", 220, 95);
        g.drawString("Quit", 220, 130);
        g.drawRect(220, 50, 100, 20);

        int mX = Mouse.getX();
        int mY = Mouse.getY();
        if((mX>220&&mX<320) && (mY>640-70 && mY<640-50)){ // play button
            if(Mouse.isButtonDown(0)){
                game.getState(PLAY_STATE).init(gameContainer, game);
//                ((PlayScreen) game.getState(PLAY_STATE)).startGame();
                game.enterState(1);
            }
        }else if((mX>220&&mX<335) && (mY>640-105 && mY<640-90)) { //back to menu button
        	if(Mouse.isButtonDown(0)) {
        		 gameContainer.reinit();
        	}
        }else if((mX>220&&mX<320) && (mY>640-145 && mY<640-125)) {
        	if(Mouse.isButtonDown(0)) {
        		gameContainer.exit();
        	}
        }

        messageField.render(gameContainer, g);
        messages.render(gameContainer, g);
        g.setColor(Color.black);
        g.drawLine(GAME_WIDTH, messages.getHeight(), WINDOW_WIDTH, messages.getHeight());
        g.setColor(Color.white);
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int delta){
        if (client != null) {
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
    }

    public void addChatClient(ChatClient client) {
        this.client = client;
    }

    public int getID(){
        return GAME_OVER_STATE;
    }
}
