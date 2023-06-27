package graphics_view.graphical_controller;

import controller.controller.*;

import static controller.view_controllers.Utilities.getAllBuildingNames;

import graphics_view.view.InitialMenu;
import graphics_view.view.ShopMenu;
import graphics_view.view.TradeMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.*;
import model.Cell;
import model.building.*;
import model.castle_components.CastleComponent;
import model.man.Man;
import model.man.SoldierType;

import java.util.*;

public class GameController {
    @FXML
    private Button dropRock;
    @FXML
    private Button dropTree;
    @FXML
    private Button dropBuilding;
    @FXML
    private Button dropUnit;
    @FXML
    private Button setTexture;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane infoPane;
    @FXML
    private Button endTurnButton;
    @FXML
    private Button enterTradeMenu;
    @FXML
    private Button enterShopMenu;
    @FXML
    private TilePane gameMap;
    private HBox monarchyInfo = new HBox();
    private HBox selectedTilesInfo = new HBox();

    private Match match;
    private VBox rateInfo, popularityInfo, foodInfo, goldPopulationInfo;
    private CoreGameMenuController controller;
    private double tileSize = 30.0;
    private GameMap mapData;
    public static final HashMap<Cell, StackPane> cellToTile = new HashMap<>();
    public static final HashMap<StackPane, Cell> tileToCell = new HashMap<>();
    private double x = -1, y = -1;
    StackPane origin = null, destination = null;
    private List<StackPane> selectedTiles = new ArrayList<>();
    private CoreMapEditMenuController coreMapEditMenuController;
    private int count;
    private CoreSelectUnitMenuController unitController;
    private ArrayList<Selectable> selectedUnits = new ArrayList<>();
    private boolean unitSelected;
    private Cell targetCell;
    private String taskName;
    private CoreSelectBuildingMenuController coreSelectBuildingMenuController;

    public void init(Match match) {
        this.match = match;
        coreMapEditMenuController = new CoreMapEditMenuController(match, null);
        mapData = match.getCurrentMatchMap();
        this.controller = new CoreGameMenuController(match, null);
        initiateGameMap();
        monarchyInfo.setSpacing(35);
        rateInfo = new VBox();
        popularityInfo = new VBox();
        foodInfo = new VBox();
        goldPopulationInfo = new VBox();
        monarchyInfo.getChildren().addAll(rateInfo, popularityInfo, foodInfo, goldPopulationInfo);
        refreshRateInfoPane();
        monarchyInfo.setLayoutX(320);
        monarchyInfo.setLayoutY(80);
        selectedTilesInfo.setLayoutX(320);
        selectedTilesInfo.setLayoutY(80);
        unitSelected = false;
        selectedTilesInfo.setSpacing(35);
    }

    private void refreshSelectedTilesInfo(List<StackPane> tiles) {
        List<Man> allMen = new ArrayList<>();
        for (StackPane tile : tiles) {
            Cell cell = tileToCell.get(tile);
            allMen.addAll(cell.getMen());
        }
        allMen.removeIf(x -> x.getOwner() != match.getCurrentUser());
        HashMap<SoldierType, Integer> manCount = new HashMap<>();
        for (SoldierType type: SoldierType.values()) {
            int count = 0;
            for (Man man: allMen) {
                if (man.getName().equals(type.getName())) {
                    count++;
                }
            }
            if (count == 0)continue;
            manCount.put(type, count);
        }

        if (selectedTilesInfo == null) {
            selectedTilesInfo = new HBox();
            selectedTilesInfo.setSpacing(35);
            selectedTilesInfo.setLayoutX(320);
            selectedTilesInfo.setLayoutY(80);
        } else {
            selectedTilesInfo.getChildren().clear();
        }

        VBox soldiers = new VBox();
        soldiers.getChildren().add(new Label("units to be selected:"));
        List<Map.Entry> soldierList = new ArrayList<>(manCount.entrySet());
        Collections.sort(soldierList, Comparator.comparingInt(x -> (int) x.getValue()));
        for (Map.Entry entry : soldierList) {
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            soldiers.getChildren().add(hBox);
            SoldierType type = (SoldierType) entry.getKey();
            int val = (int) entry.getValue();
            Label label = new Label(type.getName() + " : " + val);
            label.setMinWidth(70);
            hBox.getChildren().add(label);
            TextField count = new TextField();
            count.setPrefWidth(25);
            count.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(
                        ObservableValue<? extends String> observable,
                        String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        count.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            hBox.getChildren().add(count);
            Button button = new Button("Select");
            button.setOnAction(event -> {
                if (count.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("invalid number");
                    alert.showAndWait();
                    return;
                }
                int cnt = Integer.parseInt(count.getText());
                count.setText("");
                if (cnt <= 0 || cnt > val) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("invalid number");
                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(cnt + " " + type.getName() + " selected");
                alert.showAndWait();
                unitSelected = true;

                selectedUnits.clear();
                int i = 0;
                for (Man man : allMen) {
                    if (i == cnt) break;
                    if (man.getName().equals(type.getName())) selectedUnits.add(man);
                    i++;
                }
                assignTask(type);
            });
            hBox.getChildren().add(button);
        }
        while (infoPane.getChildren().size() > 9) {
            infoPane.getChildren().remove(9);
        }
        infoPane.getChildren().add(selectedTilesInfo);
        ScrollPane scrollSoldiers = new ScrollPane(soldiers);
        scrollSoldiers.setMaxHeight(120);
        scrollSoldiers.setMinWidth(200);
        selectedTilesInfo.getChildren().add(scrollSoldiers);

        VBox buildings = new VBox();
        buildings.getChildren().add(new Label("buildings to be selected:"));
        for (StackPane selectedTile: selectedTiles) {
            Cell cell = tileToCell.get(selectedTile);
            if (cell.getBuilding() == null || cell.getBuilding().getOwner() != match.getCurrentUser()) {
                continue;
            }
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            Label e = new Label(cell.getBuilding().getName());
            e.setMinWidth(65);
            hBox.getChildren().add(e);
            Button button = new Button("Select");
            button.setOnAction(event -> {
                handleSelectedBuildingRequests(cell, cell.getBuilding());
            });
            hBox.getChildren().add(button);
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            buildings.getChildren().add(hBox);
        }
        ScrollPane scrollBuildings = new ScrollPane(buildings);
        scrollBuildings.setMinWidth(180);
        scrollBuildings.setMaxHeight(120);
        selectedTilesInfo.getChildren().add(scrollBuildings);
    }

    private void handleSelectedBuildingRequests(Cell cell, Building building) {
        coreSelectBuildingMenuController = new CoreSelectBuildingMenuController(
                building, null, match.getCurrentMonarchy());
        VBox vBox = new VBox();
        if (building instanceof CastleComponent) {
            Button button = new Button("repair");
            vBox.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(coreSelectBuildingMenuController.repair());
                    alert.showAndWait();
                }
            });
        }
        else if (building instanceof CivilBuilding
                && ((CivilBuilding) building).getCivilType() == CivilBuildingType.BARRACKS) {
            handleUnitCreationBuilding(vBox, SoldierType.getSpecifiedTroops("european"), cell);
        }
        else if (building instanceof CivilBuilding
                && ((CivilBuilding) building).getCivilType() == CivilBuildingType.MERCENARY_POST) {
            handleUnitCreationBuilding(vBox, SoldierType.getSpecifiedTroops("arab"), cell);
        }
        else return;
        popUpHandler(vBox);
    }

    private void handleUnitCreationBuilding(VBox vBox, ArrayList<SoldierType> list, Cell cell) {
        count = 1;
        for (SoldierType soldierType : list) {
            Button button = new Button(soldierType.getName());
            vBox.getChildren().add(button);

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(coreSelectBuildingMenuController.createUnit(soldierType, count));
                    mountCellData(cellToTile.get(cell), cell);
                    alert.showAndWait();
                }
            });
        }

        vBox.getChildren().add(getNumberButton());
    }

    private void assignTask(SoldierType type) {
        unitController = new CoreSelectUnitMenuController(selectedUnits , match , null ,
                match.getCurrentUser() , mapData , type);
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (selectedUnits.size() == 0) return;
                String keyName = keyEvent.getCode().getName();
                switch (keyName) {
                    case "M":
                        taskName = "move";
                        break;
                    case "F":
                        taskName = "attack";
                        break;
                    case "P":
                        taskName = "patrol";
                        break;
                    case "A":
                        if (type == null || type.range == 0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Invalid Command");
                            alert.setContentText("The selected unit is unable to do this task");
                            alert.showAndWait();
                            unitSelected = false;
                            return;
                        }
                        taskName = "air strike";
                        break;
                }
            }
        });
    }

    private void initMove(Cell targetCell) {
        unitController.moveTo(targetCell.getXCoordinate() , targetCell.getYCoordinate());
        System.out.println("Move!!");
        unitSelected = false;
    }

    private void initAttack(Cell targetCell) {
        unitController.attackByEnemy(targetCell.getXCoordinate() , targetCell.getYCoordinate());
        System.out.println("Attack!!");
        unitSelected = false;
    }

    private void initPatrol(Cell targetCell1 , Cell targetCell2) {
        unitController.patrol(targetCell1.getXCoordinate() , targetCell1.getYCoordinate() ,
                targetCell2.getXCoordinate() , targetCell2.getYCoordinate());
        System.out.println("Patrol!!");
        unitSelected = false;
    }
    private void initAirStrike(Cell targetCell) {
        unitController.attackByXY(targetCell.getXCoordinate() , targetCell.getYCoordinate());
        System.out.println("Air Strike!!");
        unitSelected = false;
    }




    private void initiateGameMap() {
        gameMap.setVgap(1);
        gameMap.setHgap(1);
        gameMap.setPrefRows(mapData.getHeight() / 10);
        gameMap.setPrefColumns(mapData.getWidth() / 10);
        gameMap.setPrefTileHeight(tileSize);
        gameMap.setPrefTileWidth(tileSize);
        gameMap.setLayoutX(0);
        gameMap.setLayoutY(0);
        mountTiles(mapData);
        mountZoomFeature();
        mountNavigationFeature();
    }

    private void mountNavigationFeature() {
        mainPane.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.SECONDARY) return;

            int amount = 10;

            if (mouseEvent.getX() > x + tileSize) {
                gameMap.setLayoutX(Math.min(gameMap.getLayoutX() + amount, (gameMap.getWidth() / 2) * (gameMap.getScaleX() - 1)));
            } else if (mouseEvent.getX() < x - tileSize) {
                gameMap.setLayoutX(
                        Math.max(
                            gameMap.getLayoutX() - amount
                            , 1200 - gameMap.getWidth() * gameMap.getScaleX() + (gameMap.getWidth() / 2) * (gameMap.getScaleX() - 1))
                        );
            }
            if (mouseEvent.getY() > y + tileSize) {
                gameMap.setLayoutY(Math.min(gameMap.getLayoutY() + amount, (gameMap.getHeight() / 2) * (gameMap.getScaleY() - 1)));
            } else if (mouseEvent.getY() < y - tileSize){
                gameMap.setLayoutY(
                        Math.max(
                                gameMap.getLayoutY() - amount
                                , 500 - gameMap.getHeight() * gameMap.getScaleY() + (gameMap.getHeight() / 2) * (gameMap.getScaleY() - 1))
                );
            }
        });

        mainPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getX();
                y = mouseEvent.getY();
            }
        });
    }

    private void mountZoomFeature() {
        int maxZoom = 5;
        gameMap.setOnScroll(scrollEvent -> {
            if (scrollEvent.getDeltaY() >= 0) {
                gameMap.setScaleX(gameMap.getScaleX() + ((gameMap.getScaleX() + 0.1 > maxZoom) ? 0 : 0.1));
                gameMap.setScaleY(gameMap.getScaleY() + ((gameMap.getScaleY() + 0.1 > maxZoom) ? 0 : 0.1));
            } else {
                gameMap.setScaleX(gameMap.getScaleX() - ((gameMap.getScaleX() - 0.1 < 1) ? 0 : 0.1));
                gameMap.setScaleY(gameMap.getScaleY() - ((gameMap.getScaleY() - 0.1 < 1) ? 0 : 0.1));

                if (gameMap.getLayoutX() > (gameMap.getWidth() / 2) * (gameMap.getScaleX() - 1))
                    gameMap.setLayoutX((gameMap.getWidth() / 2) * (gameMap.getScaleX() - 1));
                else if (gameMap.getLayoutX() < 1200 - gameMap.getWidth() * gameMap.getScaleX() + gameMap.getWidth() / 2 * (gameMap.getScaleX() - 1))
                    gameMap.setLayoutX(1200 - gameMap.getWidth() * gameMap.getScaleX() + gameMap.getWidth() / 2 * (gameMap.getScaleX() - 1));

                if (gameMap.getLayoutY() > (gameMap.getHeight() / 2) * (gameMap.getScaleY() - 1))
                    gameMap.setLayoutY((gameMap.getHeight() / 2) * (gameMap.getScaleY() - 1));
                else if (gameMap.getLayoutY() < 500 - gameMap.getHeight() * gameMap.getScaleY() + gameMap.getHeight() / 2 * (gameMap.getScaleY() - 1))
                    gameMap.setLayoutY(500 - gameMap.getHeight() * gameMap.getScaleY() + gameMap.getHeight() / 2 * (gameMap.getScaleY() - 1));
            }
        });
    }


    private void mountTiles(GameMap selectedMap) {
        for (int i = selectedMap.getHeight() / 10 - 1; i >= 0 ;i--) {
            for (int j = 0; j < selectedMap.getWidth() / 10; j++) {
                StackPane tile = getTile(selectedMap.getCell(j, i));
                gameMap.getChildren().add(tile);
                cellToTile.put(selectedMap.getCell(j, i), tile);
                tileToCell.put(tile, selectedMap.getCell(j, i));
            }
        }
    }

    private StackPane getTile(Cell cell) {
        StackPane tile = new StackPane();
        tile.setMinWidth(tileSize);
        tile.setMaxWidth(tileSize);
        tile.setMinHeight(tileSize);
        tile.setMaxHeight(tileSize);
        mountCellData(tile, cell);
        return tile;
    }

    public void mountCellData(StackPane tile, Cell cell) {
        tile.getChildren().clear();
        tile.setBackground(new Background(new BackgroundFill(cell.getType().getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        if (cell.getNaturalEntityType() != null)
            tile.getChildren().add(cell.getNaturalEntityType().getPicture(tile));
        if (cell.getBuilding() != null)
            tile.getChildren().add(cell.getBuilding().getPicture(tile));
        if (cell.getMen().size() != 0)
            tile.getChildren().add(cell.getMen().get(0).getPicture(tile));
        defineClickEvents(tile);
    }

    public void refreshRateInfoPane() {
        while (infoPane.getChildren().size() > 9) {
            infoPane.getChildren().remove(9);
        }
        infoPane.getChildren().add(monarchyInfo);
        Monarchy monarchy = match.getCurrentUser().getMonarchy();
        rateInfo.getChildren().clear();
        HBox taxInfo = new HBox();
        taxInfo.setAlignment(Pos.CENTER);
        taxInfo.setSpacing(5);
        taxInfo.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        Button ITaxButton = new Button("+"), DTaxButton = new Button("-");
        Label taxLabel = new Label("Tax: " + monarchy.getTaxRate());
        taxLabel.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        taxLabel.setMinWidth(100);
        taxLabel.setAlignment(Pos.CENTER);
        ITaxButton.setOnAction(event -> {
            controller.setTaxRate("" + (monarchy.getTaxRate() + 1));
            taxLabel.setText("Tax: " + monarchy.getTaxRate());
            refreshPopularityInfo();
        });
        DTaxButton.setOnAction(event -> {
            controller.setTaxRate("" + (monarchy.getTaxRate() - 1));
            taxLabel.setText("Tax: " + monarchy.getTaxRate());
            refreshPopularityInfo();
        });
        taxInfo.getChildren().addAll(DTaxButton, taxLabel, ITaxButton);
        HBox fearInfo = new HBox();
        fearInfo.setAlignment(Pos.CENTER);
        fearInfo.setSpacing(5);
        fearInfo.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        Button IFearButton = new Button("+"), DFearButton = new Button("-");
        Label fearLabel = new Label("Fear: " + monarchy.getFearRate());
        fearLabel.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        fearLabel.setMinWidth(100);
        fearLabel.setAlignment(Pos.CENTER);
        IFearButton.setOnAction(event -> {
            controller.setFearRate("" + (monarchy.getFearRate() + 1));
            fearLabel.setText("Fear: " + monarchy.getFearRate());
            refreshPopularityInfo();
        });
        DFearButton.setOnAction(event -> {
            controller.setFearRate("" + (monarchy.getFearRate() - 1));
            fearLabel.setText("Fear: " + monarchy.getFearRate());
            refreshPopularityInfo();
        });
        fearInfo.getChildren().addAll(DFearButton, fearLabel, IFearButton);
        HBox foodInfo = new HBox();
        foodInfo.setAlignment(Pos.CENTER);
        foodInfo.setSpacing(5);
        foodInfo.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        Button IFoodButton = new Button("+"), DFoodButton = new Button("-");
        Label foodLabel = new Label("Food: " + monarchy.getFoodRate());
        foodLabel.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        foodLabel.setMinWidth(100);
        foodLabel.setAlignment(Pos.CENTER);
        IFoodButton.setOnAction(event -> {
            controller.setFoodRate("" + (monarchy.getFoodRate() + 1));
            foodLabel.setText("Food: " + monarchy.getFoodRate());
            refreshPopularityInfo();
        });
        DFoodButton.setOnAction(event -> {
            controller.setFoodRate("" + (monarchy.getFoodRate() - 1));
            foodLabel.setText("Food: " + monarchy.getFoodRate());
            refreshPopularityInfo();
        });
        foodInfo.getChildren().addAll(DFoodButton, foodLabel, IFoodButton);
        Button refreshButton = new Button("refresh");
        refreshButton.setOnAction(event -> refreshRateInfoPane());
        rateInfo.getChildren().addAll(fearInfo, taxInfo, foodInfo, refreshButton);
        refreshPopularityInfo();
    }

    public void refreshPopularityInfo() {
        Monarchy monarchy = match.getCurrentMonarchy();
        popularityInfo.getChildren().clear();
        popularityInfo.getChildren().add(new Label("Popularity: " + monarchy.getPopularity()));
        Label fear = new Label("Fear:" + monarchy.calcPopularityFear());
        if (monarchy.calcPopularityFear() > 0)
            fear.setStyle("-fx-text-fill: green");
        else if (monarchy.calcPopularityFear() < 0)
            fear.setStyle("-fx-text-fill: red");
        popularityInfo.getChildren().add(fear);
        Label tax = new Label("Tax:" + monarchy.calcPopularityTax());
        if (monarchy.calcPopularityTax() > 0)
            tax.setStyle("-fx-text-fill: green");
        else if (monarchy.calcPopularityTax() < 0)
            tax.setStyle("-fx-text-fill: red");
        popularityInfo.getChildren().add(tax);
        Label food = new Label("Food:" + monarchy.calcPopularityFood());
        if (monarchy.calcPopularityFood() > 0)
            food.setStyle("-fx-text-fill: green");
        else if (monarchy.calcPopularityFood() < 0)
            food.setStyle("-fx-text-fill: red");
        popularityInfo.getChildren().add(food);
        Label religion = new Label("Religion:" + monarchy.calcPopularityReligion());
        if (monarchy.calcPopularityReligion() > 0)
            religion.setStyle("-fx-text-fill: green");
        else if (monarchy.calcPopularityReligion() < 0)
            religion.setStyle("-fx-text-fill: red");
        popularityInfo.getChildren().add(religion);
        Label total = new Label("Total:" + monarchy.calcPopularity());
        if (monarchy.calcPopularity() > 0)
            total.setStyle("-fx-text-fill: green");
        else if (monarchy.calcPopularity() < 0)
            total.setStyle("-fx-text-fill: red");
        popularityInfo.getChildren().add(total);

        foodInfo.getChildren().clear();
        Label totalLabel = new Label("Total food: " + monarchy.getFood());
        foodInfo.getChildren().add(totalLabel);
        Label wheat = new Label("Wheat: " + monarchy.getGood(GoodsType.WHEAT));
        foodInfo.getChildren().add(wheat);
        Label meat = new Label("Meat: " + monarchy.getGood(GoodsType.MEAT));
        foodInfo.getChildren().add(meat);
        Label bread = new Label("Bread: " + monarchy.getGood(GoodsType.BREAD));
        foodInfo.getChildren().add(bread);
        Label cheese = new Label("Cheese: " + monarchy.getGood(GoodsType.CHEESE));

        foodInfo.getChildren().add(cheese);

        goldPopulationInfo.getChildren().clear();
        HBox goldInfo = new HBox();
        Label gold = new Label("Gold: " + monarchy.getGold());
        goldInfo.getChildren().add(gold);
        Label deltaGold;
        if (monarchy.getTotalTax() > 0){
            deltaGold = new Label("+" + monarchy.getTotalTax());
            deltaGold.setStyle("-fx-text-fill: green");
            goldInfo.getChildren().add(deltaGold);
        } else if (monarchy.getTotalTax() < 0){
            deltaGold = new Label("" + monarchy.getTotalTax());
            deltaGold.setStyle("-fx-text-fill: red");
            goldInfo.getChildren().add(deltaGold);
        }
        HBox populationInfo = new HBox();
        goldPopulationInfo.getChildren().add(goldInfo);
        Label population = new Label("Population: " + monarchy.getPopulation());
        populationInfo.getChildren().add(population);
        Label deltaPopulation;
        if (monarchy.getGrowthRate() > 0) {
            deltaPopulation = new Label("+" + monarchy.getGrowthRate());
            deltaPopulation.setStyle("-fx-text-fill: green");
            populationInfo.getChildren().add(deltaPopulation);
        } else if (monarchy.getGrowthRate() < 0) {
            deltaPopulation = new Label("" + monarchy.getGrowthRate());
            deltaPopulation.setStyle("-fx-text-fill: red");
            populationInfo.getChildren().add(deltaPopulation);
        }
        goldPopulationInfo.getChildren().add(populationInfo);
    }

    private void defineClickEvents(StackPane tile) {
        Tooltip tooltip = new Tooltip();
        tooltip.setShowDelay(Duration.millis(1500));
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setHideOnEscape(true);
        tooltip.setHideDelay(Duration.ZERO);
        Tooltip.install(tile, tooltip);
        tooltip.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                tooltip.setText(tileToCell.get(tile).showDetails());
            }
        });

        tile.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        });

        tile.setOnDragDropped(event -> {
            if (event.getGestureSource() != tile && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
                String buildingName = event.getDragboard().getString();
                Cell cell = tileToCell.get(tile);
                String xStr = String.valueOf(cell.getLocation().x);
                String yStr = String.valueOf(cell.getLocation().y);
                String log = controller.dropBuilding(xStr, yStr, buildingName);
                if (!log.equals("success\n")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(log);
                    alert.showAndWait();
                }
                mountCellData(cellToTile.get(cell), cell);
                event.consume();
            }
            event.consume();
        });

        tile.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.PRIMARY)return;
            int rows = gameMap.getPrefRows();
            int columns = gameMap.getPrefColumns();
            if (event.getClickCount() == 2) {
                if (origin == null) {
                    for (StackPane selectedTile : selectedTiles) {
                        selectedTile.setStyle("-fx-border-color: #41bcef");
                    }
                    disableButtons();
                    selectedTiles.clear();
                    origin = tile;
                    System.out.println(tile);
                } else {
                    for (StackPane selectedTile : selectedTiles) {
                        selectedTile.setStyle("-fx-border-color: transparent");
                    }
                    selectedTiles.clear();
                    destination = tile;
                    System.out.println(tile);
                    int index = gameMap.getChildren().indexOf(origin);
                    int x1 = index % columns;
                    int y1 = rows - (index / columns) - 1;
                    index = gameMap.getChildren().indexOf(destination);
                    int x2 = index % columns;
                    int y2 = rows - (index / columns) - 1;
                    int xMin = Math.min(x1, x2);
                    int xMax = Math.max(x1, x2);
                    int yMin = Math.min(y1, y2);
                    int yMax = Math.max(y1, y2);
                    for (int i = xMin; i <= xMax; i++) {
                        for (int j = yMin; j <= yMax; j++) {
                            StackPane tileInRange = getTileByLocation(i, j);
                            if (tileInRange != null) {
                                tileInRange.setStyle("-fx-border-color: red");
                                selectedTiles.add(tileInRange);
                            }
                        }
                    }
                    refreshSelectedTilesInfo(selectedTiles);
                    origin = null;
                }
            }
            if (event.getClickCount() == 1 && !unitSelected) {
                if (selectedTiles.contains(tile) && selectedTiles.size() == 1) {
                    tile.setStyle("-fx-border-color: transparent");
                    selectedTiles.remove(tile);
                    disableButtons();
                    refreshRateInfoPane();
                    return;
                }
                for (StackPane selectedTile : selectedTiles) {
                    selectedTile.setStyle("-fx-border-color: transparent");
                }
                selectedTiles.clear();
                tile.setStyle("-fx-border-color: red");
                selectedTiles.add(tile);
                refreshSelectedTilesInfo(selectedTiles);
                if (origin == null) {
                    enableButtons();
                }
            }
            else if (event.getClickCount() == 1) {
                if (taskName == null) return;
                switch (taskName) {
                    case "move":
                        targetCell = tileToCell.get(tile);
                        initMove(targetCell);
                        targetCell = null;
                        break;
                    case "attack":
                        targetCell = tileToCell.get(tile);
                        initAttack(targetCell);
                        targetCell = null;
                        break;
                    case "patrol":
                        if (targetCell == null) targetCell = tileToCell.get(tile);
                        else {
                            Cell targetCell2 = tileToCell.get(tile);
                            initPatrol(targetCell, targetCell2);
                            targetCell = null;
                        }
                        break;
                    case "air strike":
                        targetCell = tileToCell.get(tile);
                        initAirStrike(targetCell);
                        targetCell = null;
                        break;
                }
            }
        });
    }

    private StackPane getTileByLocation(int x, int y) {
        return cellToTile.get(mapData.getCell(x, y));
    }

    public void enterTrade(MouseEvent mouseEvent) throws Exception {
        new TradeMenu(match.getCurrentUser(), match).start(new Stage());
    }

    public void enterShop(MouseEvent mouseEvent) throws Exception {
        new ShopMenu().start(new Stage());
    }

    public void nextTurn(MouseEvent mouseEvent) {
        controller.nextTurn();
        this.controller = new CoreGameMenuController(match, null);
        this.coreMapEditMenuController = new CoreMapEditMenuController(match, null);
        refreshRateInfoPane();
    }

    private void enableButtons() {
        dropBuilding.setManaged(true);
        dropBuilding.setVisible(true);
        dropUnit.setVisible(true);
        dropUnit.setManaged(true);
        setTexture.setManaged(true);
        setTexture.setVisible(true);
        dropTree.setManaged(true);
        dropTree.setVisible(true);
        dropRock.setManaged(true);
        dropRock.setVisible(true);
    }

    private void disableButtons() {
        dropBuilding.setManaged(false);
        dropBuilding.setVisible(false);
        dropUnit.setVisible(false);
        dropUnit.setManaged(false);
        setTexture.setManaged(false);
        setTexture.setVisible(false);
        dropTree.setManaged(false);
        dropTree.setVisible(false);
        dropRock.setManaged(false);
        dropRock.setVisible(false);
    }

    public void dropBuilding(MouseEvent mouseEvent) {
        Cell cellToModify = tileToCell.get(selectedTiles.get(0));
        VBox vBox = new VBox();
        TilePane selectionTilePane = new TilePane();
        selectionTilePane.setPrefColumns(10);
        HashMap<Object, String> buildingsData = getAllBuildingNames();
        for (Map.Entry<Object, String> buildingType : buildingsData.entrySet()) {
            Button button = new Button(buildingType.getValue());
            selectionTilePane.getChildren().add(button);

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert result = new Alert(Alert.AlertType.INFORMATION);
                    result.setContentText(coreMapEditMenuController.dropBuilding(cellToModify.getLocation(), buildingType.getKey()));
                    mountCellData(selectedTiles.get(0), cellToModify);
                    result.showAndWait();
                }
            });
        }
        vBox.getChildren().add(selectionTilePane);
        popUpHandler(vBox);
    }

    public void dropUnit(MouseEvent mouseEvent) {
        count = 1;
        Cell cellToModify = tileToCell.get(selectedTiles.get(0));
        TilePane selectionTilePane = new TilePane();
        selectionTilePane.setPrefColumns(10);
        VBox vBox = new VBox();
        vBox.getChildren().add(selectionTilePane);
        for (SoldierType soldierType : SoldierType.values()) {
            Button button = new Button(soldierType.getName());
            selectionTilePane.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert result = new Alert(Alert.AlertType.INFORMATION);
                    result.setContentText(
                            coreMapEditMenuController.dropUnit(cellToModify.getLocation(), soldierType, count));
                    mountCellData(selectedTiles.get(0), cellToModify);
                    result.showAndWait();
                }
            });
        }


        vBox.getChildren().add(getNumberButton());
        popUpHandler(vBox);
    }

    private Button getNumberButton() {
        int max = 21;
        Button number = new Button(String.valueOf(count));
        number.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                count++;
                if (count == (max + 1)) count = 1;
                number.setText(String.valueOf(count));
            }
        });
        return number;
    }

    public void setTexture(MouseEvent mouseEvent) {
        Cell cellToModify = tileToCell.get(selectedTiles.get(0));
        VBox vBox = new VBox();
        for (LandType landType : LandType.values()) {
            Button button = new Button(landType.getTypeName());
            vBox.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert result = new Alert(Alert.AlertType.INFORMATION);
                    result.setContentText(coreMapEditMenuController.setTexture(cellToModify.getLocation(), landType));
                    mountCellData(selectedTiles.get(0), cellToModify);
                    result.showAndWait();
                }
            });
        }
        Button button = new Button("reset tile");
        vBox.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert result = new Alert(Alert.AlertType.INFORMATION);
                result.setContentText(coreMapEditMenuController.clear(cellToModify.getLocation()));
                mountCellData(selectedTiles.get(0), cellToModify);
                result.showAndWait();
            }
        });
        popUpHandler(vBox);
    }

    private void popUpHandler(VBox vBox) {
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(200);
        vBox.setMinHeight(200);
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(InitialMenu.class.getResource("/assets/logo.jpeg").toExternalForm()));
        stage.setTitle("StrongHold Crusader");
        stage.setScene(scene);
        stage.show();
    }

    public void dropTree(MouseEvent mouseEvent) {
        Cell cellToModify = tileToCell.get(selectedTiles.get(0));
        VBox vBox = new VBox();
        for (NaturalEntityType treeType : NaturalEntityType.getTreeTypes()) {
            Button button = new Button(treeType.getNaturalEntityName());
            vBox.getChildren().add(button);

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert result = new Alert(Alert.AlertType.INFORMATION);
                    result.setContentText(coreMapEditMenuController.dropTree(cellToModify.getLocation(), treeType));
                    mountCellData(selectedTiles.get(0), cellToModify);
                    result.showAndWait();
                }
            });
        }
        popUpHandler(vBox);
    }

    public void dropRock(MouseEvent mouseEvent) {
        Cell cellToModify = tileToCell.get(selectedTiles.get(0));
        VBox vBox = new VBox();
        for (NaturalEntityType rockType: NaturalEntityType.getRockTypes()) {
            Button button = new Button(rockType.getNaturalEntityName());
            vBox.getChildren().add(button);

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert result = new Alert(Alert.AlertType.INFORMATION);
                    result.setContentText(coreMapEditMenuController.dropRock(cellToModify.getLocation(), rockType.getDirection()));
                    mountCellData(selectedTiles.get(0), cellToModify);
                    result.showAndWait();
                }
            });
        }
        popUpHandler(vBox);
    }

    public void enterBuild(MouseEvent mouseEvent) {
        TabPane pane = new TabPane();
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab civilTab = new Tab("civil");
        Tab engineerTab = new Tab("engineer");
        Tab producerTab = new Tab("producer");
        ScrollPane civilPane = new ScrollPane();
        ScrollPane engineerPane = new ScrollPane();
        ScrollPane producerPane = new ScrollPane();
        civilTab.setContent(civilPane);
        engineerTab.setContent(engineerPane);
        producerTab.setContent(producerPane);
        pane.getTabs().addAll(civilTab, engineerTab, producerTab);

        HBox civilHBox = new HBox();
        HBox engineerHBox = new HBox();
        HBox producerHBox = new HBox();

        civilPane.setContent(civilHBox);
        engineerPane.setContent(engineerHBox);
        producerPane.setContent(producerHBox);

        civilHBox.setSpacing(3);
        engineerHBox.setSpacing(3);
        producerHBox.setSpacing(3);

        civilHBox.setAlignment(Pos.CENTER);
        engineerHBox.setAlignment(Pos.CENTER);
        producerHBox.setAlignment(Pos.CENTER);

        for (CivilBuildingType type :CivilBuildingType.values()) {
            Image image = Building.getImage(type.getName());
            String name = type.getName();
            Rectangle rectangle = getDragRectangle(image, name);
            civilHBox.getChildren().add(rectangle);
        }

        for (EngineerBuildingType type :EngineerBuildingType.values()) {
            Image image = Building.getImage(type.getName());
            String name = type.name();
            Rectangle rectangle = getDragRectangle(image, name);
            engineerHBox.getChildren().add(rectangle);
        }

//        for (ProductionBuildingType type :ProductionBuildingType.values()) {
//            Image image = Building.getImage(type.getName());
//            String name = type.name();
//            Rectangle rectangle = getDragRectangle(image, name);
//            producerHBox.getChildren().add(rectangle);
//        }

        Popup  popup = new Popup();
        popup.getContent().add(pane);
        popup.show(Utilities.getStage());
    }

    private static Rectangle getDragRectangle(Image image, String name) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(new ImagePattern(image));
        rectangle.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard dragboard = rectangle.startDragAndDrop(TransferMode.ANY);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(name);
                dragboard.setContent(clipboardContent);
                dragboard.setDragView(image);
            }
        });
        return rectangle;
    }
}
