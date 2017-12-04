public interface Constants {
    final String GAME_NAME = "Python Master Race";

    final int CHAT_PORT = 11000;
    final int GAME_PORT = 10000;
    final int GAME_CLIENT_PORT = 8000;

    final int BUFFER_SIZE = 99999;

    final int GAME_WIDTH = 480;
    final int CHAT_WIDTH = 320;
    final int WINDOW_WIDTH = 800;
    final int WINDOW_HEIGHT = 640;

    // State identifiers
    final int MENU_STATE = 0;   //menu state identifier
    final int PLAY_STATE = 1;   //play state identifier
    final int GAME_OVER_STATE = 2;   //game over state identifier
    final int LOBBY_STATE = 3;   // lobby state identifier
    final int WAITING_STATE = 4; // waiting state identifier
    final int INST_STATE = 5; // instruction state identifier
    
    // Game stages
    final int GAME_START = 0;
    final int IN_PROGRESS = 1;
    final int GAME_END = 2;
    final int WAITING_FOR_PLAYERS = 3;
    final int GAME_READY = 4;

    final int POWER_UP = 0;
    final int OBSTACLE = 1;
}
