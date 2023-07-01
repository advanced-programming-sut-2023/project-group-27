package server;

import com.google.gson.Gson;
import controller.Controller;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.MessengerWrapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class Server {
    ServerSocket server;
    List<Connection> connectionList = new ArrayList<>();
    List<Connection> connectionChatList = new ArrayList<>();
    List<GameRequest> gameRequests = new ArrayList<>();
    Lobby lobby;
    HashMap<Connection, User> connectionToUser = new HashMap<>();
    HashMap<User, Connection> userToConnection = new HashMap<>();

    HashMap<Connection, User> connectionChatToUser = new HashMap<>();
    HashMap<User, Connection> userToConnectionChat = new HashMap<>();

    public Server(int port) throws IOException {
        this.lobby = new Lobby();
        this.server = new ServerSocket(port);
        System.out.println("server started ...");
        new Thread(() -> {
            while(true) {
                try {
                    Socket socket1 = server.accept();
                    Socket socket2 = server.accept();
                    Socket socketChat1 = server.accept();
                    Socket socketChat2 = server.accept();
                    Connection connection = new Connection(socket2, socket1);
                    Connection connectionChat = new Connection(socketChat2, socketChat1);
                    String username = connection.getResponse();
                    System.out.println("connection added " + username);

                    connectionToUser.put(connection, StrongholdCrusader.getUserByName(username));
                    userToConnection.put(StrongholdCrusader.getUserByName(username), connection);

                    connectionChatToUser.put(connectionChat, StrongholdCrusader.getUserByName(username));
                    userToConnectionChat.put(StrongholdCrusader.getUserByName(username), connectionChat);

                    connectionList.add(connection);
                    connectionChatList.add(connectionChat);
                    new Thread(() -> {
                        System.out.println("listening to " + username);
                        while (true) {
                            String command;
                            try {
                                command = connection.listen();
                                System.out.println("command received from " + username + " : " + command);
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
                                GameRequest request = new GameRequest(owner, capacity,
                                        mapIndex, StrongholdCrusader.getPort(), visibility.equals("public"));
                                request.addPlayer(owner);
                                gameRequests.add(request);
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
                                            playerConnection.request(connectionToUser.get(connection).getUsername());
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
                                            }
                                            try {
                                                request.getGameServer().addConnection();
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            playerConnection.request(String.valueOf(request.getPort()));
                                            try {
                                                request.getGameServer().stableConnection();
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        request.getGameServer().run();
                                    }
                                }
                            }

                        }
                    }).start();
                    new Thread(()-> {
                        while (true) {
                            try {
                                String command = connectionChat.listen();
                                if (serverCommands.UPDATE_CHAT.getMatcher(command).matches()) {
                                    User owner = connectionChatToUser.get(connectionChat);
                                    String jsonString = null;
                                    try {
                                        jsonString = connectionChat.listen();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    for (User user : StrongholdCrusader.getAllUsers().values()) {
                                        try {
                                            if (user.equals(owner)) continue;
                                            Connection connectionSend = userToConnectionChat.get(user);
                                            connectionSend.response(jsonString);
                                            System.out.println("Messenger data send to others...");
                                        } catch (Exception ignored) {
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    public static void main(String[] args) throws IOException {
        Controller.fetchData();
        new Server(8080);
    }
}
