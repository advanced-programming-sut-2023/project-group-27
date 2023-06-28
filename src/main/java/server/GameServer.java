package server;

import java.io.IOException;
import java.util.List;

public class GameServer extends Thread{
    int capacity;
    boolean isPublic;
    List<Connection> connectionList;
    long creationTime;

    public GameServer(int capacity, int mapIndex, boolean isPublic) throws IOException {
        this.creationTime = System.currentTimeMillis() / 1000;
        this.isPublic = isPublic;
        this.capacity = capacity;
    }

    public void addConnection(Connection connection) {
        connectionList.add(connection);
    }

    public void removeConnection(Connection connection) {
        connectionList.remove(connection);
    }
}
