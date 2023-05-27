package graphics_view.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class ProfileMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TabPane tabPane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/profileMenu.fxml"));

        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }
}
