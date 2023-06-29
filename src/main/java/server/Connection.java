package server;

import model.StrongholdCrusader;
import model.User;
import model.man.SoldierType;

import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket1, socket2;
    private BufferedReader in1, in2;
    private PrintWriter out1, out2;
    private User user;

    public Connection(Socket socket1, Socket socket2) throws IOException {
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.out1 = new PrintWriter(socket1.getOutputStream(), true);
        this.in1 = new BufferedReader(new java.io.InputStreamReader(socket1.getInputStream()));
        this.out2 = new PrintWriter(socket2.getOutputStream(), true);
        this.in2 = new BufferedReader(new java.io.InputStreamReader(socket2.getInputStream()));
    }

    public void response(String data) {
        out1.println(data);
    }

    public String listen() throws IOException {
        return in1.readLine();
    }

    public void request(String data) {
        out2.println(data);
    }

    public String getResponse() throws IOException {
        return in2.readLine();
    }

    public User getUser() {
        return user;
    }
}
