package graphics_view.view;

import controller.controller.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class RegisterMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
        URL url = getClass().getResource("/fxml/RegisterMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
