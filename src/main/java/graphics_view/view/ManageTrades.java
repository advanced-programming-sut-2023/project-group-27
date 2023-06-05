package graphics_view.view;

import graphics_view.graphical_controller.ManageTradeController;
import graphics_view.graphical_controller.TradeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Match;
import model.User;

import java.net.URL;

public class ManageTrades extends Application {
    private User loggedInUser;
    private Match match;

    public ManageTrades(User loggedInUser, Match match) {
        this.loggedInUser = loggedInUser;
        this.match = match;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/fxml/ManageTrades.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane borderPane = loader.load();
        ((ManageTradeController) loader.getController()).init(loggedInUser, match);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
