package graphics_view.graphical_controller;

import controller.controller.CoreShopMenuController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GoodsType;
import model.Match;
import model.StrongholdCrusader;
import model.User;

import java.util.ArrayList;

public class ShopController {
    private final CoreShopMenuController controller;
    @FXML
    private VBox vBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button closeButton;
    private Label goldAmount;
    private final User currentUser;
    private final ArrayList<Row> allRows;

    public ShopController() {
        // TODO : update match in StrongHoldCrusader
        Match match = StrongholdCrusader.getCurrentMatch();
        controller = new CoreShopMenuController(match,null);
        currentUser = match.getCurrentUser();
        allRows = new ArrayList<>();
    }

    public void initialize() {
        goldAmount = (Label) vBox.getChildren().get(1);
        goldAmount.setText(currentUser.getUsername() + "'s Gold = " + currentUser.getMonarchy().getGold());
        int i = 1;
        for (GoodsType type : GoodsType.values()) {
            Node name = getNodeFromGridPane(gridPane, 0, i); //goods name
            assert name != null;
            ((Label) name).setText(type.getName());

            Node owned = getNodeFromGridPane(gridPane, 1, i); //owned amount
            assert owned != null;
            ((Label) owned).setText(Integer.toString(currentUser.getMonarchy().getGood(type)));
            Label ownedAmount = (Label) owned;

            Node buyPrice = getNodeFromGridPane(gridPane, 2, i); //buy price
            assert buyPrice != null;
            ((Label) buyPrice).setText(Integer.toString(type.getShopBuyPrice()));

            Node sellPrice = getNodeFromGridPane(gridPane, 3, i); //sell price
            assert sellPrice != null;
            ((Label) sellPrice).setText(Integer.toString(type.getShopSellPrice()));

            Node amountSlider = getNodeFromGridPane(gridPane, 4, i); //amount slider
            assert amountSlider != null;
            Slider slider = (Slider) ((HBox) amountSlider).getChildren().get(0);
            Label label = (Label) ((HBox) amountSlider).getChildren().get(1);
            slider.setMaxWidth(130);
            slider.setMax(20);
            slider.setMin(0);
            slider.setValue(0);
            slider.setBlockIncrement(1.0);
            slider.setMajorTickUnit(5.0);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setSnapToTicks(true);

            slider.valueProperty().addListener(
                    (observable, oldValue, newValue) ->
            label.setText(Integer.toString((int) Math.floor(newValue.doubleValue()))));

            Node node = getNodeFromGridPane(gridPane , 5 , i); //buy button
            assert node != null;
            Button buyButton = (Button) node;

            node = getNodeFromGridPane(gridPane , 6 , i); //sell button
            assert node != null;
            Button sellButton = (Button) node;
            Row row = new Row(buyButton, sellButton, slider, ownedAmount, type);
            allRows.add(row);
            i++;
        }
        startShop();
    }

    private void startShop() {
        for (Row row : allRows) {
            row.buyButton.setOnAction(actionEvent -> {
                int amount = (int) Math.floor(row.amountSlider.getValue());
                String result = controller.buy(row.type , amount);
                Alert alert;
                if (result.equals("Buy successful!")) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Buy Successful");
                    alert.setContentText(row.type.getName() + " Bought Successfully!");
                    row.ownedAmount.setText(Integer.toString(currentUser.getMonarchy().getGood(row.type)));
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Buy Failed");
                    alert.setContentText(result);
                }
                alert.showAndWait();
                goldAmount.setText(currentUser.getUsername() + "'s Gold = " + currentUser.getMonarchy().getGold());
            });

            row.sellButton.setOnAction(actionEvent -> {
                int amount = (int) Math.floor(row.amountSlider.getValue());
                String result = controller.sell(row.type , amount);
                Alert alert;
                if (result.equals("Sell successful!")) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Sell Successful");
                    alert.setContentText(row.type.getName() + " Sold Successfully!");
                    row.ownedAmount.setText(Integer.toString(currentUser.getMonarchy().getGood(row.type)));
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Sell Failed");
                    alert.setContentText(result);
                }
                alert.showAndWait();
                goldAmount.setText(currentUser.getUsername() + "'s Gold = " + currentUser.getMonarchy().getGold());
            });

        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void exitShop(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private class Row {
        private final Button buyButton;
        private final Button sellButton;
        private final Slider amountSlider;
        private final Label ownedAmount;
        private final GoodsType type;

        public Row(Button buyButton, Button sellButton, Slider amountSlider, Label ownedAmount, GoodsType type) {
            this.buyButton = buyButton;
            this.sellButton = sellButton;
            this.amountSlider = amountSlider;
            this.ownedAmount = ownedAmount;
            this.type = type;
        }
    }
}
