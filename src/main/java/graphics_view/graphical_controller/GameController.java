package graphics_view.graphical_controller;

import controller.controller.CoreGameMenuController;
import controller.controller.Utilities;
import graphics_view.view.ShopMenu;
import graphics_view.view.TradeMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
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

    public void init(Match match) {
        this.match = match;
        this.controller = new CoreGameMenuController(match, null);

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
