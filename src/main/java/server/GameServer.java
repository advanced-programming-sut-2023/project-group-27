package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class GameServer extends Thread{
    int capacity;
    boolean isPublic;
    ServerSocket server;
    List<Connection> connectionList;

    public GameServer(int port, int capacity, int mapIndex, boolean isPublic) throws IOException {
        this.isPublic = isPublic;
        this.capacity = capacity;
        this.server = new ServerSocket(port);
        while(true) {
            server.accept();
        }
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
