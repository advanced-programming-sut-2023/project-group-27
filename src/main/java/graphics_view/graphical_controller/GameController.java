package graphics_view.graphical_controller;

import controller.controller.CoreGameMenuController;
import controller.controller.Utilities;
import graphics_view.view.ShopMenu;
import graphics_view.view.TradeMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.GameMap;
import model.Match;

public class GameController {
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

    private Match match;
    private CoreGameMenuController controller;
    private double tileSize = 30.0;
    GameMap mapData = match.getCurrentMatchMap();

    public void init(Match match) {
        this.match = match;
        this.controller = new CoreGameMenuController(match, null);
        initiateGameMap();
    }

    private void initiateGameMap() {
        gameMap.setVgap(0);
        gameMap.setHgap(0);
        gameMap.setPrefRows(mapData.getHeight());
        gameMap.setPrefColumns(mapData.getWidth());
        gameMap.setPrefTileHeight(tileSize);
        gameMap.setPrefTileWidth(tileSize);
        mountTiles(mapData);
    }

    private void mountTiles(GameMap selectedMap) {
        for (int i = selectedMap.getHeight() - 1; i >= 0 ;i--) {
            for (int j = 0; j < selectedMap.getWidth(); j++) {
                StackPane tile = getTile(selectedMap.getCell(j, i));
                gameMap.getChildren().add(tile);
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
//        tile.getChildren().add(cell.getType().getPicture());
//        tile.getChildren().add(cell.getNaturalEntityType().getPicture());
//        tile.getChildren().add(cell.getBuilding().getPicture());
//        tile.getChildren().add(cell.getMen().get(0).getPicture());
        defineClickEvents(tile);
    }

    private void defineClickEvents(StackPane tile) {
        //TODO define events such as select, etc...
    }

    private StackPane getTileByLocation(int x, int y) {
        return mapData.getCell(x, y).getStackPane();
    }

    public void enterTrade(MouseEvent mouseEvent) throws Exception {
        new TradeMenu(match.getCurrentUser(), match).start(Utilities.getStage());
    }

    public void enterShop(MouseEvent mouseEvent) throws Exception {
        new ShopMenu().start(Utilities.getStage());
    }

    public void nextTurn(MouseEvent mouseEvent) {
        controller.nextTurn();
    }
}
