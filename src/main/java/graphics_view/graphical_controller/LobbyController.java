package graphics_view.graphical_controller;

import com.google.gson.Gson;
import controller.controller.Utilities;
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
import model.StrongholdCrusader;
import model.User;
import org.w3c.dom.Text;
import server.Connection;
import server.GameRequest;

import java.io.IOException;
import java.net.Socket;
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
        gameRequests = gson.fromJson(jsonString, List.class);

    }

    private HBox getRequestUI(GameRequest gameRequest) {
        Connection mainServer = Utilities.getMainServer();
        HBox hBox = new HBox();
        Circle circle = new Circle(10);
        circle.setFill(new ImagePattern(gameRequest.getOwner().getAvatar()));
        Label label = new Label(gameRequest.getOwner().getNickname() + "'s game");
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setContentText("waiting for other players");
                alert.showAndWait();
                new Thread(() -> {
                    while (true) {
                        String command;
                        try {
                                command = mainServer.listen();
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
                            String mapIndex, connectionString;
                            try {
                                mapIndex = mainServer.listen();
                                connectionString = mainServer.listen();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            int mapIndexInt = Integer.parseInt(mapIndex);
                            User owner = gameRequest.getOwner();
                            Connection gameConnection = gson.fromJson(connectionString, Connection.class);

                        }
                    }
                });
            }
        });
        hBox.getChildren().addAll(circle, label, button);
        return hBox;
    }
}
