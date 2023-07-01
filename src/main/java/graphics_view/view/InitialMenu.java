package graphics_view.view;

import com.google.gson.Gson;
import controller.Controller;
import controller.controller.Utilities;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;
import model.chat.Messenger;
import model.chat.MessengerWrapper;
import server.Connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;

public class InitialMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
        stage.getIcons().add(new Image(InitialMenu.class.getResource("/assets/logo.jpeg").toExternalForm()));
        stage.setTitle("StrongHold Crusader");
        Utilities.init();
        if (stayLoggedInCheck() != null) {
            Socket socket1 = new Socket("localhost", 8080);
            Socket socket2 = new Socket("localhost", 8080);
            Connection connection = new Connection(socket1, socket2);
            Utilities.setMainServer(connection);
            connection.response(StrongholdCrusader.getLoggedInUser().getUsername());

            Socket socketChat1 = new Socket("localhost", 8080);
            Socket socketChat2 = new Socket("localhost", 8080);
            Connection connectionChat = new Connection(socketChat1, socketChat2);
            Utilities.setChatRoomConnection(connectionChat);
            new Thread(()-> {
               while (true) {
                   try {
                       System.out.println("JESUS CHRIST");
                       String result = connectionChat.getResponse();
                       Gson gson = new Gson();
                       System.out.println("json : " + result);
                       MessengerWrapper wrapper = gson.fromJson(result , MessengerWrapper.class);
                       Messenger.initialize(wrapper);
                       Platform.runLater(new Runnable() {
                           @Override
                           public void run() {
                               try {
                                   new ChatMenu().start(Utilities.getStage());
                               } catch (Exception e) {
                                   throw new RuntimeException(e);
                               }
                           }
                       });
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
               }
            }).start();
            new MainMenu().start(stage);
            return;
        }
        URL url = getClass().getResource("/fxml/InitialMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
    public void enterLoginMenu() throws Exception {
        new LoginMenu().start(Utilities.getStage());
    }

    public void enterRegisterMenu() throws Exception {
        new RegisterMenu().start(Utilities.getStage());
    }

    public void exit() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Controller.pushData();
            System.exit(0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Controller.fetchData();
        launch(args);
    }

    private User stayLoggedInCheck(){
        for (User user : StrongholdCrusader.getAllUsers().values())
            if (user.isStayLoggedIn()) {
                StrongholdCrusader.setLoggedInUser(user);
                return user;
            }

        return null;
    }
}
