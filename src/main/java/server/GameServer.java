package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class GameServer extends Thread {
    int capacity;
    boolean isPublic;
    List<Connection> connectionList;
    long creationTime;

    public GameServer(int capacity, int mapIndex, boolean isPublic) throws IOException {
        this.creationTime = System.currentTimeMillis() / 1000;
        this.isPublic = isPublic;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        for (Connection connection : connectionList) {
            Thread thread = new Thread(() -> {
                while (true) {
                    String command = null;
                    try {
                        command = connection.receive();
                        for (Connection connection1 : connectionList) {
                            if (connection1 != connection) {
                                connection1.send(command);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(command);
                }
            });
        }

    }

    public void addConnection(Connection connection) {
        connectionList.add(connection);
    }

    public void removeConnection (Connection connection){
        connectionList.remove(connection);
    }
}
