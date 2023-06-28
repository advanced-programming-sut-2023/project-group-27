package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class GameServer extends Thread{
    int capacity;
    ServerSocket server;
    List<Connection> connectionList;

    public GameServer(int port, int capacity, int mapIndex) throws IOException {
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
        System.out.println("starting GameServer");

        for (Connection connection: connectionList) {
            Thread thread = new Thread(() -> {
                while (true) {
                    String command = null;
                    try {
                        command = connection.receive();
                        for (Connection connection1: connectionList) {
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
}
