package graphics_view.view;

import controller.Controller;
import controller.controller.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

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
}
