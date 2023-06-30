package server;

import model.StrongholdCrusader;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameRequest {
    private User owner;
    private int capacity;
    private int mapIndex;
    private int port;
    private transient GameServer gameServer;

    private List<User> players = new ArrayList<>();

    public GameRequest(User owner, int capacity, int mapIndex, int port, boolean isPublic) {
        this.port = port;
        this.owner = owner;
        this.capacity = capacity;
        this.mapIndex = mapIndex;
        this.gameServer = new GameServer(port);
    }

    public GameServer getGameServer() {
        return gameServer;
    }

    public User getOwner() {
        return owner;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMapIndex() {
        return mapIndex;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameRequest) {
            GameRequest gameRequest = (GameRequest) obj;
            return gameRequest.owner.getUsername().equals(owner.getUsername())
                    && gameRequest.capacity == capacity && gameRequest.mapIndex == mapIndex;
        }
        return false;
    }

    public void addPlayer(User player) {
        players.add(player);
    }

    public List<User> getPlayers() {
        return players;
    }
}
