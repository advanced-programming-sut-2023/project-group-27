package graphics_view.view;

import controller.controller.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class InitialMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
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
}
