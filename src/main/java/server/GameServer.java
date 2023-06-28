package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class GameServer extends Thread{
    int capacity;
    boolean isPublic;
    ServerSocket server;
    List<Connection> connectionList;
    long creationTime;

    public GameServer(int port, int capacity, int mapIndex, boolean isPublic) throws IOException {
        this.creationTime = System.currentTimeMillis() / 1000;
        this.isPublic = isPublic;
        this.capacity = capacity;
        this.server = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (connectionList.size() < capacity) {
            try {
                Connection connection = new Connection(server.accept());
                connectionList.add(connection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
