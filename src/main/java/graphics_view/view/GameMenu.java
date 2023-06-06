package graphics_view.view;

import controller.controller.Utilities;
import graphics_view.graphical_controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameMenu extends Application {
    private Match match;
    {
        Location[] locations = new Location[] {new Location(1, 1), new Location(10, 10)};
        GameMap gameMap = new GameMap(100, 100, "myMap", 100, locations);
        User user = new User("user", "Password@1", "n", "s", "e", "a", "b");
        User user2 = new User("user2", "Password@1", "n", "s", "e", "a", "b");
        user.setMonarchy(new Monarchy(user, MonarchyColorType.BLACK, gameMap, locations[0]));
        user2.setMonarchy(new Monarchy(user2, MonarchyColorType.RED, gameMap, locations[1]));
        User[] users = new User[]{user, user2};
        this.match = new Match(gameMap, Arrays.stream(users).collect(Collectors.toList()));
    }
//    public GameMenu(Match match) {
//        this.match = match;
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Pane pane = loader.load();
        ((GameController) loader.getController()).init(match);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
