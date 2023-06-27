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
    private static Stage stage;
    public TradeMenu(User loggedInUser, Match match) {
        this.user = loggedInUser;
        this.match = match;
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Utilities.setStage(stage);
        TradeMenu.stage = stage;
        URL url = getClass().getResource("/fxml/TradeMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        ((TradeController) loader.getController()).init(user, match);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return TradeMenu.stage;
    }
}
