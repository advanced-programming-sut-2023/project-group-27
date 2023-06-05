package graphics_view.graphical_controller;

import controller.controller.CoreGameStartMenuController;
import javafx.fxml.Initializable;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CancellationException;

public class GameStartController implements Initializable {
    private final CoreGameStartMenuController controller =
            new CoreGameStartMenuController(null, StrongholdCrusader.getLoggedInUser());
    private final GameMap[] allMaps;
    private final MonarchyColorType[] monarchyColorTypes;
    private final ArrayList<User> thisGamePlayers;
    private final HashMap<User, MonarchyColorType> colors;
    private final User[] allUsers;
    private final HashMap<User, Cell> keepsCells;
    private GameMap selectedMap;

    public GameStartController() {
        this.allMaps = controller.getAllMaps();
        this.monarchyColorTypes = controller.getColorTypes();
        this.thisGamePlayers = controller.getThisGamePlayers();
        this.colors = controller.getColors();
        this.allUsers = controller.getAllUsers();
        this.keepsCells = controller.getKeepCells();
        controller.selectMap(0);
        this.selectedMap = controller.getSelectedMap();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
