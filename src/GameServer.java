import java.net.InetAddress;
import java.util.ArrayList;

public class GameServer implements Constants {
    ArrayList<Player> players = new ArrayList<Player>();

    public GameServer() {
        System.out.println("Game server started");
        NetworkHelper.setIsHost(true);
    }

    // helper method for broadcasting to all players
    public void broadcast(Packet packet) {
        for (Player player : players) {
            send(player, packet);
        }
    }

    public void send(Player player, Packet packet) {
        try {
            NetworkHelper.serverSend(packet, player.getAddress(), player.getPort());
        }
        catch (Exception e) {}
    }

    // methods called from other classes
    public void connectPlayer(String name, InetAddress address, int port) {
        players.add(new Player(name, address, port));
        if (players.size() > 2) {
            WaitingScreen.setGameState(GAME_READY);
        }
        broadcast(new Packet("PLAYERS", new PlayersPacket(players)));
    }

    public void startGame() {
        broadcast(new Packet("START_GAME", new StartGamePacket()));
    }
}
