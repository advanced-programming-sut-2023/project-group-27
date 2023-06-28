package graphics_view.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ChatMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TabPane tabPane = FXMLLoader.load(
                ChatMenu.class.getResource("/fxml/ChatMenu.fxml"));
        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }
}
