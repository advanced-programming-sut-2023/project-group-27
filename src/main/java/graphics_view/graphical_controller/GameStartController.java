package graphics_view.graphical_controller;

import controller.controller.CoreGameStartMenuController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
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
    private final User[] allUsersList;
    private final HashMap<User, Cell> keepsCells;
    @FXML
    private VBox selectedPlayers;
    @FXML
    private VBox allUsersVBox;
    @FXML
    private ScrollPane allUsers;
    @FXML
    private Label miniMapInfo;
    private GameMap selectedMap;
    private int selectedMapIndex = 0;

    public GameStartController() {
        this.allMaps = controller.getAllMaps();
        this.monarchyColorTypes = controller.getColorTypes();
        this.thisGamePlayers = controller.getThisGamePlayers();
        this.colors = controller.getColors();
        this.allUsersList = controller.getAllUsers();
        this.keepsCells = controller.getKeepCells();
        controller.selectMap(0);
        this.selectedMap = controller.getSelectedMap();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO show mini map at beginning too
        mountAllUsers();
    }

    private void mountAllUsers() {
        int index = 1;
        for (User user : allUsersList) {
            Button button = new Button(user.getUsername());
            int finalIndex = index;
            button.setOnMouseClicked(mouseEvent -> {
                controller.addPlayer(finalIndex);
                updateSelectedPlayers();
            });
            allUsersVBox.getChildren().add(button);
            index++;
        }
    }

    private void updateSelectedPlayers() {
        selectedPlayers.getChildren().clear();
        int index = 1;
        for (User user : thisGamePlayers) {
            Button button = new Button(user.getUsername());
            int finalIndex = index;
            button.setOnMouseClicked(mouseEvent -> {
                controller.removePlayer(finalIndex);
                updateSelectedPlayers();
            });

            HBox hBox = new HBox();
            Button button1 = new Button();
            if (colors.containsKey(user))
                button1.setText(colors.get(user).getColorName());
            else {
                MonarchyColorType monarchyColorType = getFreeColorType();
                controller.setColor(finalIndex, monarchyColorType);
            }
            button1.setOnMouseClicked(mouseEvent -> toggleColor());

            Button button2 = new Button();
            hBox.getChildren().addAll(button, button1, button2);
            allUsersVBox.getChildren().add(hBox);
            index++;
        }
    }

    public void toggleMap(MouseEvent mouseEvent) {
        selectedMapIndex = (selectedMapIndex + 1) % allMaps.length;
        controller.selectMap(selectedMapIndex + 1);
        selectedMap = controller.getSelectedMap();
        updateMiniMap();
        if (selectedMap.getCapacity() < thisGamePlayers.size()) {
            for (int i = thisGamePlayers.size(); i >= selectedMap.getCapacity(); i--)
                controller.removePlayer(i);
            updateSelectedPlayers();
        }
    }
}