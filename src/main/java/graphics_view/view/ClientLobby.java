package graphics_view.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientLobby extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(ClientLobby.class.getResource("/fxml/Lobby.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
