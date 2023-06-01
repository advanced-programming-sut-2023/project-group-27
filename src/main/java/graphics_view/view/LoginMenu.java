package graphics_view.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage = new Stage();
        URL url = getClass().getResource("/fxml/LoginMenu.fxml");
        BorderPane loginPane = FXMLLoader.load(url);
        Scene scene = new Scene(loginPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
