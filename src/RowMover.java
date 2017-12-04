import com.sun.rowset.internal.Row;

import java.util.ArrayList;

public class RowMover extends Thread implements Constants {
    ArrayList<Player> players;
    ArrayList<GameObject> row = null;
    RowGenerator gen = null;
    int seconds = 0;

    public RowMover(ArrayList<Player> players){
        this.players = players;
        gen = new RowGenerator(players);
        row = new ArrayList<>();
    }

    public void run() {
        gen.start();
        while (true) {
            if (row.size() > 0) {
                for (int i = 0; i < row.size(); i++) {
                    GameObject go = row.get(i);
//                if(go.collide(snek)) {
                    go.moveY();
//                }
                    if (go.getYpos() > WINDOW_HEIGHT) row.remove(i);

                    if (seconds % 1000 == 0 && seconds > 0) {
                        GameClient.getGameServer().objectUpdate(go.getId(), go.getHealth(), go.getXpos(), go.getYpos());
                    }
                }
                gen.setGameObjects(row);
            }
//            if (row != null)
//                System.out.println(gen.getGameObjects());
//                System.out.println(row);
//            }
        }
    }
}
