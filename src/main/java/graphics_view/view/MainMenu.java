package graphics_view.view;

import controller.controller.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import server.Connection;

import java.net.Socket;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/mainMenu.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
