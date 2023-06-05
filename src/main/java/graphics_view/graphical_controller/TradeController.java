package graphics_view.graphical_controller;

import controller.controller.CoreTradeMenuController;
import controller.controller.Utilities;
import graphics_view.view.CreateTradeMenu;
import graphics_view.view.ManageTrades;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Match;
import model.User;

public class TradeController {
    public ListView userList;
    private User loggedInUser;
    private Match match;
    private CoreTradeMenuController controller;

    public void init(User loggedInUser, Match match) {
        this.loggedInUser = loggedInUser;
        this.match = match;
        this.controller = new CoreTradeMenuController(match, loggedInUser, null);
    }


    public void create(MouseEvent mouseEvent) throws Exception {
        new CreateTradeMenu(loggedInUser, match).start(Utilities.getStage());
    }

    public void manage(MouseEvent mouseEvent) throws Exception {
        new ManageTrades(loggedInUser, match).start(Utilities.getStage());
    }
}
