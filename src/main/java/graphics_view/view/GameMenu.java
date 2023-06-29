package graphics_view.view;

import controller.controller.Utilities;
import graphics_view.graphical_controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import model.man.Soldier;
import model.man.SoldierType;
import server.Connection;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameMenu extends Application {
    private Match match;
    private Connection connection;
    public GameMenu(Match match, Connection connection) {
        this.connection = connection;
        this.match = match;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Pane pane = loader.load();
        ((GameController) loader.getController()).init(match, connection);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
