import java.io.Serializable;
import java.util.ArrayList;

public class PlayersPacket implements Serializable {
    private ArrayList<Player> players;

    public PlayersPacket(ArrayList<Player> players){
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
