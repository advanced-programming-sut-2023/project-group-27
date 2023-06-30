package graphics_view.graphical_controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.controller.CoreGameMenuController;
import controller.controller.CoreGameStartMenuController;
import controller.controller.Utilities;
import graphics_view.view.GameMenu;
import graphics_view.view.MainMenu;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Match;
import model.MonarchyColorType;
import model.StrongholdCrusader;
import model.User;
import org.w3c.dom.Text;
import server.Connection;
import server.GameRequest;
import server.GameServer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LobbyController {
    public ScrollPane serversToJoin;
    private Socket serverSocket;

    public Button createServer;
    public TextField searchTextField;

    private List<GameRequest> gameRequests;

    public void createServer(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(getCreateServerBox()));
        stage.show();
    }

    public void search(MouseEvent mouseEvent) {
        String idToSearch = searchTextField.getText();
        //TODO send id to server
    }

    private VBox getCreateServerBox() {
        VBox vBox = new VBox();
        CheckBox isPublicCheckBox = new CheckBox("private");
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    if (Integer.parseInt(t1) > 8 || Integer.parseInt(t1) < 2)
                        textField.clear();
                }
                catch (Exception exception) {
                    textField.clear();
                }
            }
        });
        Label label = new Label("#server id");//Server id generated
        Button button = new Button("create");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText().equals(""))
                    return;
                boolean isPublic = !isPublicCheckBox.isSelected();
                String serverId = label.getText();
                int capacity = Integer.parseInt(textField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //TODO send to server
                alert.setTitle("confirmation");
                alert.setHeaderText("Server created");
                alert.setContentText(label.getText());
                alert.showAndWait();
            }
        });
        HBox hBox = new HBox(new Label("capacity"),textField);
        vBox.getChildren().addAll(isPublicCheckBox, hBox, label, button);
        return vBox;
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        Connection mainServer = Utilities.getMainServer();
        mainServer.request("get games");
        String jsonString  = mainServer.getResponse();
        Gson gson = new Gson();
        Type listOfMyClassObject = new TypeToken<List<GameRequest>>() {}.getType();
        gameRequests = gson.fromJson(jsonString, listOfMyClassObject);
        VBox vBox = new VBox();
        for (GameRequest gameRequest : gameRequests) {
            vBox.getChildren().add(getRequestUI(gameRequest));
        }
        serversToJoin.setContent(vBox);
    }

    private HBox getRequestUI(GameRequest gameRequest) {
        Connection mainServer = Utilities.getMainServer();
        HBox hBox = new HBox();
        hBox.setMinWidth(200);
        hBox.setSpacing(10);
        Circle circle = new Circle(10);
        circle.setFill(new ImagePattern(gameRequest.getOwner().getAvatar()));
        Label label = new Label(gameRequest.getOwner().getNickname() + "'s game");
        label.setMinWidth(100);
        Button button = new Button("join");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainServer.request("join game");
                Gson gson = new Gson();
                String jsonString = gson.toJson(gameRequest);
                mainServer.request(jsonString);
                String result ;
                try {
                    result = mainServer.getResponse();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (!result.equals("joined")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(result);
                    alert.showAndWait();
                    return;
                }
                gameRequest.addPlayer(StrongholdCrusader.getLoggedInUser());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setContentText("waiting for other players");
                alert.showAndWait();
                new Thread(() -> {
                    while (true) {
                        String command;
                        try {
                                command = mainServer.listen();
                                System.out.println("command: " + command);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (command.equals("player joined")) {
                            String username = null;
                            try {
                                username = mainServer.listen();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            User addedPlayer = StrongholdCrusader.getUserByName(username);
                            gameRequest.addPlayer(addedPlayer);
                        }
                        if (command.equals("game started")) {
                            String mapIndex, portStr;
                            try {
                                portStr = mainServer.listen();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            int mapIndexInt = gameRequest.getMapIndex();
                            User owner = gameRequest.getOwner();
                            Socket socket3, socket4;
                            Connection gameConnection;
                            try {
                                socket3 = new Socket("localhost", Integer.parseInt(portStr));
                                socket4 = new Socket("localhost", Integer.parseInt(portStr));
                                gameConnection = new Connection(socket3, socket4);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            CoreGameStartMenuController startGameController =
                                    new CoreGameStartMenuController(null, owner);
                            for (User user : gameRequest.getPlayers()) {
                                int index = StrongholdCrusader.getUsers().indexOf(user);
                                startGameController.addPlayer(index + 1);
                            }
                            startGameController.selectMap(mapIndexInt + 1);
                            ArrayList<Integer> numbers = new ArrayList<>();
                            for (int i = 0; i < gameRequest.getPlayers().size(); i++) {
                                numbers.add(i+1);
                                startGameController.setColor(i + 1, MonarchyColorType.values()[i]);
                            }
                            startGameController.assignKeepsAndStart(numbers);
                            GameMenu gameMenu = new GameMenu(startGameController.getMatch(), gameConnection);
                            Platform.runLater(() -> {
                                try {
                                    gameMenu.start(Utilities.getStage());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        hBox.getChildren().addAll(circle, label, button);
        return hBox;
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(Utilities.getStage());
    }
}
