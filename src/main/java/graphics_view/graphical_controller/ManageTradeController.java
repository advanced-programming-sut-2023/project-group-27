package graphics_view.graphical_controller;

import model.Match;
import model.User;

public class ManageTradeController {
    private User loggedInUser;
    private Match match;

    public void init(User loggedInUser, Match match) {
        this.loggedInUser = loggedInUser;
        this.match = match;
    }


}
