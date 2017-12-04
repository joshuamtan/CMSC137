
import java.awt.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InstructionScreen extends BasicGameState implements Constants {
	String input= "";
	//public static  UnicodeFont font;
	
	public InstructionScreen(int state) {
		
	}
	public void init(GameContainer gameContainer, StateBasedGame game){
	//UnicodeFont font = new UnicodeFont("Courier New", 30, true, false);
		
    }
	
	public void drawString(Graphics g, String text, int x, int y) {
		for(String line : text.split("\n")) {
			g.drawString(line, x, y);
		}
	}
		
	public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException{
		//g.drawString(input, 180, 100);
        //g.drawString(GAME_NAME, 180, 210);
		g.drawString("GAME MECHANICS", 200, 50);
		g.drawString("The game starts immediately, with the snake \nhaving a life points of 10, rendering block \nnumbers randomly, where there is always a \npossible route forward.", 50, 90);
		g.drawString("The objective of the game is to be the last \nplayer standing while traversing the course \nby passing through numbered blocks using \nthe snake�s life to penetrate.", 50, 190);
		g.drawString("The player will be scored based on distance \nreached and remaining snake life.", 50, 290);
		g.drawString("The game only ends when each of the players' \nsnake's length or life is extinguished.", 50, 350);
		g.drawString("The snake is controlled by the arrow buttons", 50, 410);
		g.drawString("Back to Menu", 200, 500);
		int mX = Mouse.getX();
	    int mY = Mouse.getY();
		
	    
	    
	    if((mX>220&&mX<335) && (mY>640-520 && mY<640-500)) {
	    	if(Mouse.isButtonDown(0)) {
	    		gameContainer.reinit();
	    	}
	    }
	        
	}
	 
	public void update(GameContainer gameContainer, StateBasedGame game, int delta){

	}

	public int getID(){
		return INST_STATE;
	}
}
