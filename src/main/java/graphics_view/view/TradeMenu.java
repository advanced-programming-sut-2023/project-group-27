package graphics_view.view;

import controller.controller.CoreTradeMenuController;
import controller.controller.Utilities;
import graphics_view.graphical_controller.SecurityQuestionController;
import graphics_view.graphical_controller.TradeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TradeMenu extends Application {
    private final User user;
    private final Match match;
//    {
//        Location[] locations = new Location[] {new Location(1, 1), new Location(10, 10)};
//        GameMap gameMap = new GameMap(100, 100, "myMap", 100, locations);
//        this.user = new User("user", "Password@1", "n", "s", "e", "a", "b");
//        User user2 = new User("user2", "Password@1", "n", "s", "e", "a", "b");
//        user.setMonarchy(new Monarchy(user, MonarchyColorType.BLACK, gameMap, locations[0]));
//        user2.setMonarchy(new Monarchy(user2, MonarchyColorType.RED, gameMap, locations[1]));
//        User[] users = new User[]{user, user2};
//        this.match = new Match(gameMap, Arrays.stream(users).collect(Collectors.toList()));
//        CoreTradeMenuController controller1 = new CoreTradeMenuController(match, user, null);
//        CoreTradeMenuController controller2 = new CoreTradeMenuController(match, user2, null);
//        controller1.tradeRequest(user2.getUsername(), "Wheat", "1", "1", "hi");
//        controller1.tradeRequest(user2.getUsername(), "Bread", "1", "1", "hi bitch");
//        controller2.tradeRequest(user.getUsername(), "Wheat", "1", "1", "hey");
//        controller2.tradeRequest(user.getUsername(), "Bread", "1", "1", "hey bitch");
//        System.out.println("1## " + user.getMonarchy().getTradingSystem().notifications());
//        System.out.println("2%% " + user2.getMonarchy().getTradingSystem().notifications());
//        // TODO : Add Thread for notifications here
//    }
    public TradeMenu(User loggedInUser, Match match) {
        this.user = loggedInUser;
        this.match = match;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
        URL url = getClass().getResource("/fxml/TradeMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        ((TradeController) loader.getController()).init(user, match);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
