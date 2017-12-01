import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Snake implements Constants {
    public int health = 50;
    public float snakeX = 240;
    public float snakeY = 500;
    public float snakeSize = 25;

    public Snake(){}

    public void render(Graphics g){
        g.drawString(Integer.toString(health), snakeX+30, snakeY);
        for(int i=0; i<health; i++){
            g.fillOval(snakeX, snakeY+(snakeSize*i), snakeSize, snakeSize);
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame game){
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_LEFT) && snakeX > 0){snakeX -= .5; }
        if (in.isKeyDown(Input.KEY_RIGHT) && snakeX < GAME_WIDTH - snakeSize){snakeX += .5; }
        if (in.isKeyDown(Input.KEY_UP) && snakeY > 0){snakeY -= .5; }
        if (in.isKeyDown(Input.KEY_DOWN) && snakeY < WINDOW_HEIGHT - snakeSize){snakeY += .5; }
        if(this.health <= 0){
            game.enterState(GAME_OVER_STATE);
        }
    }
}
