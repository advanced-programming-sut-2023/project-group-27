package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    ServerSocket server;
    List<Connection> connectionList;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
        while(true) {
            Socket incomingSocket = server.accept();
            connectionList.add(new Connection(incomingSocket));
        }
    }
}
