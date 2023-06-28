package server;

import model.man.SoldierType;

import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));

    }

    public void send(String data) {
        out.println(data);
    }

    public String receive() throws IOException {
        return in.readLine();
    }
}
