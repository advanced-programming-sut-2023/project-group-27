package graphics_view.graphical_controller;

import controller.controller.CoreGameMenuController;
import controller.controller.Utilities;
import graphics_view.view.ShopMenu;
import graphics_view.view.TradeMenu;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.GoodsType;
import model.Match;
import model.Monarchy;

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
    private VBox rateInfo, popularityInfo, foodInfo, goldPopulationInfo;
    private CoreGameMenuController controller;

    public void init(Match match) {
        this.match = match;
        this.controller = new CoreGameMenuController(match, null);
        HBox hBox = new HBox();
        hBox.setSpacing(35);
        rateInfo = new VBox();
        popularityInfo = new VBox();
        foodInfo = new VBox();
        goldPopulationInfo = new VBox();
        hBox.getChildren().addAll(rateInfo, popularityInfo, foodInfo, goldPopulationInfo);
        infoPane.getChildren().add(hBox);
        refreshRateInfoPane();
        hBox.setLayoutX(320);
        hBox.setLayoutY(80);
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


    public void enterTrade(MouseEvent mouseEvent) throws Exception {
        new TradeMenu(match.getCurrentUser(), match).start(Utilities.getStage());
    }

    public void enterShop(MouseEvent mouseEvent) throws Exception {
        new ShopMenu().start(Utilities.getStage());
    }

    public void nextTurn(MouseEvent mouseEvent) {
        controller.nextTurn();
        this.controller = new CoreGameMenuController(match, null);
        refreshRateInfoPane();
    }
}
