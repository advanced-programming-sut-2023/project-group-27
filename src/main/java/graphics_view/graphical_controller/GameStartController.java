package graphics_view.graphical_controller;

import controller.controller.CoreGameStartMenuController;
import graphics_view.view.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameStartController implements Initializable {
    private final CoreGameStartMenuController controller =
            new CoreGameStartMenuController(null, StrongholdCrusader.getLoggedInUser());
    private final GameMap[] allMaps;
    private final MonarchyColorType[] monarchyColorTypes;
    private final ArrayList<User> thisGamePlayers;
    private final HashMap<User, MonarchyColorType> colors;
    private final User[] allUsersList;
    private final HashMap<User, Integer> playersKeeps;
    @FXML
    private TilePane miniMap;
    private Cell[] keepsLocations;
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
        controller.selectMap(1);
        this.selectedMap = controller.getSelectedMap();
        this.playersKeeps = new HashMap<>();
        keepsLocations = selectedMap.getKeepsLocations();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateMiniMap();
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
            button.setMinWidth(100);
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
            if (!colors.containsKey(user)) {
                MonarchyColorType monarchyColorType = getFreeColorType();
                controller.setColor(finalIndex, monarchyColorType);
            }
            button1.setText(colors.get(user).getColorName());
            button1.setOnMouseClicked(mouseEvent -> toggleColor(user, button1));

            Button button2 = new Button();
            if (!playersKeeps.containsKey(user)) {
                Integer integer = getFreeKeepLocation();
                playersKeeps.put(user, integer);
            }
            button2.setText(keepsLocations[playersKeeps.get(user)].getLocation().toString());
            button2.setOnMouseClicked(mouseEvent -> toggleKeep(user, button2));
            hBox.getChildren().addAll(button, button1, button2);
            selectedPlayers.getChildren().add(hBox);
            index++;
        }
    }

    private void toggleKeep(User user, Button button) {
        Integer integer = getFreeKeepLocation();
        if (integer == null) return;

        playersKeeps.remove(user);
        playersKeeps.put(user, integer);
        button.setText(keepsLocations[integer].getLocation().toString());
    }

    private void toggleColor(User user, Button button) {
        MonarchyColorType monarchyColorType = getFreeColorType();
        if (monarchyColorType == null) return;

        colors.remove(user);
        colors.put(user, monarchyColorType);
        button.setText(monarchyColorType.getColorName());
    }

    private MonarchyColorType getFreeColorType() {
        for (MonarchyColorType monarchyColorType : monarchyColorTypes)
            if (!colors.containsValue(monarchyColorType)) return monarchyColorType;
        return null;
    }

    private Integer getFreeKeepLocation() {
        for (int i = 0; i < keepsLocations.length; i++)
            if (!playersKeeps.containsValue(i)) return i;
        return null;
    }

    public void toggleMap(MouseEvent mouseEvent) {
        selectedMapIndex = (selectedMapIndex + 1) % allMaps.length;
        controller.selectMap(selectedMapIndex + 1);
        selectedMap = controller.getSelectedMap();
        updateMiniMap();
        keepsLocations = selectedMap.getKeepsLocations();
        playersKeeps.clear();
        updateSelectedPlayers();
        if (selectedMap.getCapacity() < thisGamePlayers.size()) {
            for (int i = thisGamePlayers.size(); i >= selectedMap.getCapacity(); i--)
                controller.removePlayer(i);
            updateSelectedPlayers();
        }
    }

    private void updateMiniMap() {
        miniMap.setPrefColumns(selectedMap.getWidth());
        miniMap.setPrefRows(selectedMap.getHeight());
        miniMap.setPrefTileWidth(1.0);
        miniMap.setPrefTileHeight(1.0);
        miniMap.setHgap(0);
        miniMap.setVgap(0);
        miniMap.getChildren().clear();
        miniMapInfo.setText(selectedMap.getMapName() + ": " + selectedMap.getCapacity());
        for (int i = selectedMap.getHeight() - 1; i >= 0 ;i--) {
            for (int j = 0; j < selectedMap.getHeight(); j++) {
                Rectangle rectangle = new Rectangle(1, 1);
                rectangle.setFill(selectedMap.getCell(j, i).getType().getColor());
                miniMap.getChildren().add(rectangle);
            }
        }
    }

    public void start(MouseEvent mouseEvent) {
        //TODO start game;
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(new Stage());
        //TODO link current stage;
    }
}