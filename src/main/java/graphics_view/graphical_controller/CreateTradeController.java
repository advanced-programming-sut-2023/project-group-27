package graphics_view.graphical_controller;

import controller.controller.Utilities;
import graphics_view.view.TradeWithUserMenu;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import model.Match;
import model.User;

public class CreateTradeController {
    public ListView userList;
    private User loggedInUser;
    private Match match;

    public void init(User loggedInUser, Match match) {
        this.loggedInUser = loggedInUser;
        this.match = match;
        match.getMonarchies().forEach(monarchy -> userList.getItems().add(monarchy.getKing().getUsername()));
        userList.setMaxHeight(24 * match.getMonarchies().size() + 2);
        userList.setMaxWidth(100);
    }

    public void back(MouseEvent mouseEvent) {

    }

    public void next(MouseEvent mouseEvent) throws Exception {
        User user = userList.getSelectionModel().getSelectedItem() == null ? null
                : match.getMonarchies().stream().filter(
                        monarchy -> monarchy.getKing().getUsername().equals(userList.getSelectionModel().getSelectedItem()))
                .findFirst().get().getKing();
        new TradeWithUserMenu(loggedInUser, user, match).start(Utilities.getStage());
    }
}
