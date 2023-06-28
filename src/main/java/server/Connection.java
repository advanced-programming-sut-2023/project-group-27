package server;

import model.StrongholdCrusader;
import model.User;
import model.man.SoldierType;

import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private User user;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
        this.user = StrongholdCrusader.getUserByName(in.readLine());
    }

    public void send(String data) {
        out.println(data);
    }

    public String receive() throws IOException {
        return in.readLine();
    }

    public User getUser() {
        return user;
    }
}
