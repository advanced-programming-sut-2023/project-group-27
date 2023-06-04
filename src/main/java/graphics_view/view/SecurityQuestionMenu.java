package graphics_view.view;

import controller.controller.CoreRegisterMenuController;
import graphics_view.graphical_controller.SecurityQuestionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class SecurityQuestionMenu extends Application {
    private CoreRegisterMenuController controller;
    public SecurityQuestionMenu(CoreRegisterMenuController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/fxml/SecurityQuestionMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        ((SecurityQuestionController) loader.getController()).setController(controller);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
