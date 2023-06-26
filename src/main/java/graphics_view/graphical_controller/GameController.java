package graphics_view.graphical_controller;

import controller.controller.CoreGameMenuController;
import graphics_view.view.ShopMenu;
import graphics_view.view.TradeMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import model.man.Man;

import java.util.*;

public class GameController {
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
    private final HashMap<Cell, StackPane> cellToTile = new HashMap<>();
    private final HashMap<StackPane, Cell> tileToCell = new HashMap<>();
    private double x = -1, y = -1;
    StackPane origin = null, destination = null;
    private List<StackPane> selectedTiles = new ArrayList<>();

    public void init(Match match) {
        this.match = match;
        mapData = match.getCurrentMatchMap();
        this.controller = new CoreGameMenuController(match, null);
        initiateGameMap();
        monarchyInfo.setSpacing(35);
        rateInfo = new VBox();
        popularityInfo = new VBox();
        foodInfo = new VBox();
        goldPopulationInfo = new VBox();
        monarchyInfo.getChildren().addAll(rateInfo, popularityInfo, foodInfo, goldPopulationInfo);
        infoPane.getChildren().add(monarchyInfo);
        refreshRateInfoPane();
        monarchyInfo.setLayoutX(320);
        monarchyInfo.setLayoutY(80);
        selectedTilesInfo.setLayoutX(320);
        selectedTilesInfo.setLayoutY(80);
    }

    private void refreshSelectedTilesInfo(List<StackPane> tiles) {
        List<Man> allMen = new ArrayList<>();
        for (StackPane tile : tiles) {
            Cell cell = tileToCell.get(tile);
            allMen.addAll(cell.getMen());
        }
        allMen.removeIf(x -> x.getOwner() != match.getCurrentUser());
        HashMap<Man, Integer> manCount = new HashMap<>();
        for (Man man: allMen) {
            manCount.put(man, manCount.getOrDefault(man, 0) + 1);
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
            Man man = (Man) entry.getKey();
            int val = (int) entry.getValue();
            Label label = new Label(man.getName() + " : " + val);
            hBox.getChildren().add(label);
            TextField count = new TextField();
            count.setMaxWidth(50);
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
                int cnt = Integer.parseInt(count.getText());
                count.setText("");
                if (cnt <= 0 || cnt > val) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("invalid number");
                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(cnt + man.getName() + "are selected");
                alert.showAndWait();
                // TODO clear recently selected units
                // TODO add new selected units
            });
            hBox.getChildren().add(button);
        }
        infoPane.getChildren().clear();
        infoPane.getChildren().add(selectedTilesInfo);
        selectedTilesInfo.getChildren().add(soldiers);
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
        //TODO add getPicture() method to model entities.

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
        rateInfo.getChildren().addAll(fearInfo, taxInfo, foodInfo);
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
        tile.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.PRIMARY)return;
            int rows = gameMap.getPrefRows();
            int columns = gameMap.getPrefColumns();
            if (event.getClickCount() == 2) {
                if (origin == null) {
                    for (StackPane selectedTile : selectedTiles) {
                        selectedTile.setStyle("-fx-border-color: transparent");
                    }
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
            if (event.getClickCount() == 1) {
                for (StackPane selectedTile : selectedTiles) {
                    selectedTile.setStyle("-fx-border-color: transparent");
                }
                selectedTiles.clear();
                tile.setStyle("-fx-border-color: red");
                selectedTiles.add(tile);
                refreshSelectedTilesInfo(selectedTiles);
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
        refreshRateInfoPane();
    }
}
