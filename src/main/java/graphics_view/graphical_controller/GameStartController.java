package graphics_view.graphical_controller;

import com.google.gson.Gson;
import controller.controller.CoreGameStartMenuController;
import controller.controller.Utilities;
import graphics_view.view.GameMenu;
import graphics_view.view.GameStartMenu;
import graphics_view.view.MainMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.*;
import server.Connection;
import server.GameRequest;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GameStartController implements Initializable {
    private final CoreGameStartMenuController controller;
    private final GameMap[] allMaps;
    private final MonarchyColorType[] monarchyColorTypes;
    private final ArrayList<User> thisGamePlayers;
    private final HashMap<User, MonarchyColorType> colors;
    private final User[] allUsersList;
    private final HashMap<User, Integer> playersKeeps;
    public Button capacityButton;
    public Button visibilityButton;
    @FXML
    private TilePane miniMap;
    private Cell[] keepsLocations;
    @FXML
    private VBox selectedPlayers;
    @FXML
    private ScrollPane allUsers;
    @FXML
    private Label miniMapInfo;
    private GameMap selectedMap;
    private int selectedMapIndex = 0;
    private int capacity = 2;
    private int port;
    private String visibility = "public";

    public GameStartController() {
        controller = new CoreGameStartMenuController(null, StrongholdCrusader.getLoggedInUser());
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
//        mountAllUsers();
        updateSelectedPlayers();
    }

//    private void mountAllUsers() {
//        int index = 1;
//        for (User user : allUsersList) {
//            Button button = new Button(user.getUsername());
//            int finalIndex = index;
//            button.setOnMouseClicked(mouseEvent -> {
//                controller.addPlayer(finalIndex);
//                updateSelectedPlayers();
//            });
//            button.setMinWidth(100);
//            allUsersVBox.getChildren().add(button);
//            index++;
//        }
//    }

    private void updateSelectedPlayers() {
        selectedPlayers.getChildren().clear();
        int index = 1;
        for (User user : thisGamePlayers) {
            Button button = new Button(user.getUsername());
            int finalIndex = index;
            button.setOnMouseClicked(mouseEvent -> {
                if (controller.removePlayer(finalIndex).startsWith("removed "))
                    playersKeeps.remove(user);

                updateSelectedPlayers();
            });

            HBox hBox = new HBox();
            Button button1 = new Button();
            if (!colors.containsKey(user)) {
                MonarchyColorType monarchyColorType = getFreeColorType(null);
                controller.setColor(finalIndex, monarchyColorType);
            }
            button1.setText(colors.get(user).getColorName());
            button1.setOnMouseClicked(mouseEvent -> toggleColor(user, button1));

            Button button2 = new Button();
            if (!playersKeeps.containsKey(user)) {
                Integer integer = getFreeKeepLocation(null);
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
        Integer integer = getFreeKeepLocation(playersKeeps.getOrDefault(user, null));
        if (integer == null) return;

        playersKeeps.remove(user);
        playersKeeps.put(user, integer);
        button.setText(keepsLocations[integer].getLocation().toString());
    }

    private void toggleColor(User user, Button button) {
        MonarchyColorType monarchyColorType = getFreeColorType(getColorIndex(colors.get(user)));
        if (monarchyColorType == null) return;

        colors.remove(user);
        colors.put(user, monarchyColorType);
        button.setText(monarchyColorType.getColorName());
    }

    private Integer getColorIndex(MonarchyColorType monarchyColorType) {
        for (int i = 0; i < monarchyColorTypes.length; i++)
            if (monarchyColorTypes[i].equals(monarchyColorType)) return i;
        return 0;
    }

    private MonarchyColorType getFreeColorType(Integer index) {
        int i = 0;
        for (MonarchyColorType monarchyColorType : monarchyColorTypes) {
            if (!colors.containsValue(monarchyColorType) && (index == null || index < i)) return monarchyColorType;
            i++;
        }
        i = 0;
        for (MonarchyColorType monarchyColorType : monarchyColorTypes) {
            if (!colors.containsValue(monarchyColorType) && (index == null || index > i)) return monarchyColorType;
            i++;
        }
        return null;
    }

    private Integer getFreeKeepLocation(Integer current) {
        for (int i = 0; i < keepsLocations.length; i++)
            if (!playersKeeps.containsValue(i) && (current == null || current < i)) return i;
        for (int i = 0; i < keepsLocations.length; i++)
            if (!playersKeeps.containsValue(i) && (current == null || current > i)) return i;
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
            for (int j = 0; j < selectedMap.getWidth(); j++) {
                Rectangle rectangle = new Rectangle(1, 1);
                rectangle.setFill(selectedMap.getCell(j, i).getType().getColor());
                if (new ArrayList<Cell>(List.of(selectedMap.getKeepsLocations())).contains(selectedMap.getCell(j, i)))
                    rectangle.setFill(Color.RED);
                miniMap.getChildren().add(rectangle);
            }
        }
    }

    private int getCapacity() {
        return capacity;
    }

    private String getVisibility() {
        return visibility;
    }

    public void start(MouseEvent mouseEvent) {
        Utilities.getMainServer().request("start game");
    }


    public void start2() throws Exception {
        Gson gson = new Gson();
        Socket socket3, socket4;
//        Thread.sleep(1000);
        socket3 = new Socket("localhost", port);
        socket4 = new Socket("localhost", port);
        Connection connection = new Connection(socket3, socket4);
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < controller.getThisGamePlayers().size(); i++) {
            numbers.add(i+1);
            controller.setColor(i + 1, MonarchyColorType.values()[i]);
        }
        controller.assignKeepsAndStart(numbers);
        GameMenu gameMenu = new GameMenu(controller.getMatch(), connection);
        Popup popup = new Popup();
        popup.getContent().add(new Label("aaaa"));
//        popup.show(GameStartMenu.stage);
        Platform.runLater(() -> {
            try {
                gameMenu.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void enterGame(Connection connection) throws Exception {
        if (thisGamePlayers.size() < 2) return;
        ArrayList<Integer> keeps = new ArrayList<>();
        for (User user : thisGamePlayers)
            keeps.add(playersKeeps.get(user) + 1);
        controller.assignKeepsAndStart(keeps);

        new GameMenu(controller.getMatch(), connection).start(GameStartMenu.stage);
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(GameStartMenu.stage);
    }

    public void publishGame(MouseEvent mouseEvent) throws IOException {
        Utilities.getMainServer().request("create game -m " + selectedMapIndex + " -c "
                + getCapacity() + " -o " + StrongholdCrusader.getLoggedInUser().getUsername() + " -v " + getVisibility());
        Connection connection = Utilities.getMainServer();
        new Thread(() -> {
            while (true) {
                String command;
                try {
                    command = connection.listen();
                    System.out.println("command: " + command);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (command.equals("player joined")) {
                    String username = null;
                    try {
                        username = connection.listen();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    User addedPlayer = StrongholdCrusader.getUserByName(username);
                    int index = StrongholdCrusader.getUsers().indexOf(addedPlayer);
                    controller.addPlayer(index + 1);
                } else {
                    System.out.println("game port is " + port);
                    port = Integer.parseInt(command);
                    try {
                        start2();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }

    public void addCapacity(MouseEvent mouseEvent) {
        capacity++;
        capacity -= 2;
        capacity %= 7;
        capacity += 2;
        capacityButton.setText(capacity + "");
    }

    public void changeVisibility(MouseEvent mouseEvent) {
        if (visibility.equals("public")) {
            visibility = "private";
            visibilityButton.setText("private");
        } else {
            visibility = "public";
            visibilityButton.setText("public");
        }
    }
}