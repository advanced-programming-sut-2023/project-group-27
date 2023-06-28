package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class Server {
    ServerSocket server;
    List<Connection> connectionList;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
        while(true) {
            server.accept();
        }
    }
}
