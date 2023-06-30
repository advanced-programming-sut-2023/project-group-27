package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private int port;
    private List<Connection> connectionList = new ArrayList<>();
    private long creationTime;
    private ServerSocket serverSocket;
    public Socket socket1, socket2;
    private Thread thread;

    public GameServer(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.creationTime = System.currentTimeMillis() / 1000;
    }

    public void run() {
        for (Connection connection : connectionList) {
            Thread thread = new Thread(() -> {
                while (true) {
                    String command = null;
                    try {
                        command = connection.listen();
                        System.out.println("command: " + command);
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
        Socket socket1, socket2;
        GameServer gameServer = this;
        this.thread = new Thread(() -> {
            try {
                gameServer.socket1 = serverSocket.accept();
                gameServer.socket2 = serverSocket.accept();
                Connection connection = new Connection(gameServer.socket2, gameServer.socket1);
                connectionList.add(connection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    public void stableConnection() throws InterruptedException {
        thread.join();
    }
}
