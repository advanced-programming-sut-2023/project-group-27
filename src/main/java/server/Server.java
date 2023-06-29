package server;

import com.google.gson.Gson;
import model.StrongholdCrusader;
import model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class Server {
    ServerSocket server;
    List<Connection> connectionList;
    List<GameRequest> gameRequests;
    Lobby lobby;
    HashMap<Connection, User> connectionToUser;
    HashMap<User, Connection> userToConnection;

    public Server(int port) throws IOException {
        this.lobby = new Lobby();
        this.server = new ServerSocket(port);
        new Thread(() -> {
            while(true) {
                try {
                    Socket socket1 = server.accept();
                    Socket socket2 = server.accept();
                    Connection connection = new Connection(socket2, socket1);
                    String username = connection.getResponse();
                    // TODO user should sent it's username right after connecting
                    connectionToUser.put(connection, StrongholdCrusader.getUserByName(username));
                    connectionList.add(connection);
                    new Thread(() -> {
                        while (true) {
                            String command;
                            try {
                                command = connection.listen();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (serverCommands.GET_GAMES.getMatcher(command).matches()){
                                Gson gson = new Gson();
                                String json = gson.toJson(gameRequests);
                                connection.response(json);
                            }
                            if (serverCommands.CREATE_GAME.getMatcher(command).matches()) {
                                Matcher matcher = serverCommands.CREATE_GAME.getMatcher(command);
                                matcher.matches();
                                int mapIndex = Integer.parseInt(matcher.group("mapIndex"));
                                int capacity = Integer.parseInt(matcher.group("capacity"));
                                String ownerUsername = matcher.group("owner");
                                String visibility = matcher.group("visibility");
                                User owner = StrongholdCrusader.getUserByName(ownerUsername);
                                gameRequests.add(new GameRequest(owner, capacity,
                                        mapIndex, StrongholdCrusader.getPort(), visibility.equals("public")));
                            }
                            if (serverCommands.JOIN_GAME.getMatcher(command).matches()) {
                                String jsonString = null;
                                try {
                                    jsonString = connection.listen();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Gson gson = new Gson();
                                GameRequest gameRequest = gson.fromJson(jsonString, GameRequest.class);
                                for (GameRequest request : gameRequests) {
                                    if (request.equals(gameRequest)) {
                                        if (request.getPlayers().size() == request.getCapacity()) {
                                            connection.response("game is full");
                                            break;
                                        }
                                        for (User player : request.getPlayers()){
                                            Connection playerConnection = userToConnection.get(player);
                                            playerConnection.request("player joined");
                                            playerConnection.request(player.getUsername());
                                            // TODO receive this
                                        }
                                        request.addPlayer(connectionToUser.get(connection));
                                        connection.response("joined");
                                        break;
                                    }
                                }
                            }
                            if (serverCommands.START_GAME.getMatcher(command).matches()) {
                                User owner = connectionToUser.get(connection);
                                for (GameRequest request : gameRequests) {
                                    if (request.getOwner().equals(owner)) {
                                        for (User player : request.getPlayers()) {
                                            Connection playerConnection = userToConnection.get(player);
                                            if (!player.equals(owner)) {
                                                playerConnection.request("game started");
                                                playerConnection.request(request.getMapIndex() + "");
                                            }
                                            Gson gson = new Gson();
                                            Socket socket3, socket4;
                                            try {
                                                socket3 = new Socket("localhost", request.getPort());
                                                socket4 = new Socket("localhost", request.getPort());
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            Connection gameConnection = null;
                                            try {
                                                gameConnection = new Connection(socket3, socket4);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            String jsonString = gson.toJson(gameConnection);
                                            playerConnection.request(jsonString);
                                            // TODO receive this
                                        }
                                    }
                                }
                            }
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
