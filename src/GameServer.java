import java.net.InetAddress;
import java.util.ArrayList;

public class GameServer implements Constants {
    ArrayList<Player> players = new ArrayList<>();

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

    public void updatePlayer(String name, int score, Snake snake) {
        broadcast(new Packet("PLAYER_UPDATE", new PlayerUpdatePacket(name, score, snake)));
    }

    public void updateSnake(String name, float snakeHeadX, float snakeHeadY, ArrayList<SnakeBody> snakeBody, int health) {
        broadcast(new Packet("SNAKE_UPDATE", new SnakeUpdatePacket(name, snakeHeadX, snakeHeadY, snakeBody, health)));
    }

    public void objectSpawn(ArrayList<GameObject> gameObjects) {
        broadcast(new Packet("OBJECT_SPAWN", new ObjectSpawnPacket(gameObjects)));
    }

//    public void objectSpawn(int id, int type, int health, float xpos, float ypos) {
//        broadcast(new Packet("OBJECT_SPAWN", new ObjectSpawnPacket(id, type, health, xpos, ypos)));
//    }

    public void objectUpdate(int id, int health, float xpos, float ypos) {
        broadcast(new Packet("OBJECT_UPDATE", new ObjectUpdatePacket(id, health, xpos, ypos)));
    }

    public void startGame() {
        if (players.size() > 2) {
            broadcast(new Packet("START_GAME", new StartGamePacket()));
            new RowMover(players).start();
        }
    }
}
