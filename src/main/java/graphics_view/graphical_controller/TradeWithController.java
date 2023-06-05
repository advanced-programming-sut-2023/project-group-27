package graphics_view.graphical_controller;

import model.User;

public class TradeWithController {
    private model.User loggedInUser;
    private model.User user;
    private model.Match match;

    public void init(User loggedInUser, User user, model.Match match) {
        this.loggedInUser = loggedInUser;
        this.user = user;
        this.match = match;
    }


}
