package graphics_view.view;

import controller.Controller;
import graphics_view.graphical_controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;

import java.io.FileNotFoundException;
import java.net.URL;

public class LoginMenu extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        URL url = getClass().getResource("/fxml/LoginMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane loginPane = loader.load();
        Scene scene = new Scene(loginPane);

        LoginController controller = loader.getController();

        stage.setScene(scene);
        stage.show();
        controller.setStage(stage);
        controller.setScene(scene);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Controller.fetchData();
        launch(args);
    }
}
