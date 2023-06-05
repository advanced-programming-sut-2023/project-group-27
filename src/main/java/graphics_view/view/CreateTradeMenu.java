package graphics_view.view;

import graphics_view.graphical_controller.CreateTradeController;
import graphics_view.graphical_controller.TradeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Match;
import model.User;

import java.net.URL;

public class CreateTradeMenu extends Application {
    private User loggedInUser;
    private Match match;

    public CreateTradeMenu(User loggedInUser, Match match) {
        this.match = match;
        this.loggedInUser = loggedInUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/fxml/CreateTrade.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        ((CreateTradeController) loader.getController()).init(loggedInUser, match);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
