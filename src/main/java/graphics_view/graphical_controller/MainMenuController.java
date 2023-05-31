package graphics_view.graphical_controller;

import graphics_view.view.ProfileMenu;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainMenuController {
    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(new Stage());
    }
}
