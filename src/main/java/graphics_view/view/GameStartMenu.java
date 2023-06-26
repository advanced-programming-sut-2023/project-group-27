package graphics_view.view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.StrongholdCrusader;

public class GameStartMenu extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        Pane Pane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/GameStartMenu.fxml"));

        Scene scene = new Scene(Pane);
        stage.setScene(scene);
        stage.show();
    }
}
