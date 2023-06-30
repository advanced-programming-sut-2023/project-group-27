package graphics_view.view;

import controller.controller.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.chat.Reactions;

public class ChatMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage); //TODO remove this
        Reactions.init(); //TODO remove this
        TabPane tabPane = FXMLLoader.load(
                ChatMenu.class.getResource("/fxml/ChatMenu.fxml"));
        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }
}
