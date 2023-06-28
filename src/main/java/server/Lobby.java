package server;

import java.util.ArrayList;

public class Lobby {
    private final ArrayList<GameServer> gameServers;

    public Lobby() {
        gameServers = new ArrayList<>();
    }

    public void addGameServer(GameServer gameServer) {
        gameServers.add(gameServer);
    }

    public void removeGameServer(GameServer gameServer) {
        gameServers.remove(gameServer);
    }

    public ArrayList<GameServer> getGameServers() {
        return gameServers;
    }
}
