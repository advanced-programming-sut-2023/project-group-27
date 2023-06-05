package graphics_view.view;

import controller.controller.Utilities;
import graphics_view.graphical_controller.TradeController;
import graphics_view.graphical_controller.TradeWithController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Match;
import model.User;

import java.awt.desktop.AppForegroundListener;
import java.net.URL;

public class TradeWithUserMenu extends Application {
    private User loggedInUser;
    private User user;
    private model.Match match;

    public TradeWithUserMenu(User loggedInUser, User user, Match match) {
        this.loggedInUser = loggedInUser;
        this.user = user;
        this.match = match;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Utilities.setStage(stage);
        URL url = getClass().getResource("/fxml/TradeWith.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        ((TradeWithController) loader.getController()).init(loggedInUser, user, match, borderPane);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
