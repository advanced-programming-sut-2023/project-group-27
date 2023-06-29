package graphics_view.graphical_controller;

import controller.Controller;
import controller.controller.Utilities;
import graphics_view.view.ClientLobby;
import graphics_view.view.GameStartMenu;
import graphics_view.view.InitialMenu;
import graphics_view.view.ProfileMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StrongholdCrusader;

import java.io.IOException;
import java.util.Optional;

public class MainMenuController {
    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(Utilities.getStage());
    }

    public void enterGameStartMenu(MouseEvent mouseEvent) throws Exception {
        new GameStartMenu().start(Utilities.getStage());
    }

    public void logOut(MouseEvent mouseEvent) throws Exception {
        StrongholdCrusader.getLoggedInUser().setStayLoggedIn(false);
        new InitialMenu().start(Utilities.getStage());
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Controller.pushData();
            System.exit(0);
        }
    }

    public void enterLobby(MouseEvent mouseEvent) throws Exception {
        new ClientLobby().start(Utilities.getStage());
    }
}
