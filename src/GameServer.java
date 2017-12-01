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

    public void startGame() {
        broadcast(new Packet("START_GAME", new StartGamePacket()));
    }
}
