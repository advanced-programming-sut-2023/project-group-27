package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class GameServer extends Thread {
    private int port;
    private List<Connection> connectionList;
    private long creationTime;
    private ServerSocket serverSocket;

    public GameServer(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.creationTime = System.currentTimeMillis() / 1000;
    }

    @Override
    public void run() {
        for (Connection connection : connectionList) {
            Thread thread = new Thread(() -> {
                while (true) {
                    String command = null;
                    try {
                        command = connection.listen();
                        for (Connection connection1 : connectionList) {
                            if (connection1 != connection) {
                                connection1.request(command);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(command);
                }
            });
            thread.start();
        }

    }

    public void addConnection() throws IOException {
        Socket socket1 = serverSocket.accept();
        Socket socket2 = serverSocket.accept();
        Connection connection = new Connection(socket2, socket1);
        connectionList.add(connection);
    }
}
