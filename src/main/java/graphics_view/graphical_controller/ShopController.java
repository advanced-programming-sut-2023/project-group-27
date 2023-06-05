package graphics_view.graphical_controller;

import controller.controller.CoreShopMenuController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.GoodsType;
import model.StrongholdCrusader;
import model.User;

public class ShopController {
    private final CoreShopMenuController controller;
    @FXML
    private GridPane gridPane;
    private Slider slider;
    private final User currentUser;

    public ShopController() {
        controller = new CoreShopMenuController(
                StrongholdCrusader.getCurrentMatch() ,null);
        currentUser = StrongholdCrusader.getCurrentUser();
    }

    public void initialize() {
        int i = 1;
        for (GoodsType type : GoodsType.values()) {
            Node node = getNodeFromGridPane(gridPane, 0, i); //goods name
            assert node != null;
            ((Label) node).setText(type.getName());

            node = getNodeFromGridPane(gridPane, 1, i); //owned amount
            assert node != null;
            ((Label) node).setText(Integer.toString(currentUser.getMonarchy().getGood(type)));

            node = getNodeFromGridPane(gridPane, 2, i); //buy price
            assert node != null;
            ((Label) node).setText(Integer.toString(type.getShopBuyPrice()));

            node = getNodeFromGridPane(gridPane, 3, i); //sell price
            assert node != null;
            ((Label) node).setText(Integer.toString(type.getShopSellPrice()));

            node = getNodeFromGridPane(gridPane, 4, i); //amount slider
            assert node != null;
            slider = (Slider) ((HBox) node).getChildren().get(0);
            Label label = (Label) ((HBox) node).getChildren().get(1);
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

            node = getNodeFromGridPane(gridPane , 5 , i); //buy
            assert node != null;
            ((Button) node).setOnAction(actionEvent -> {
                int amount = (int) Math.floor(slider.getValue());
                String result = controller.buy(type , amount);
                Alert alert;
                if (result.equals("Buy successful!")) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Buy Successful");
                    alert.setContentText(type.getName() + " Bought Successfully!");
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Buy Failed");
                    alert.setContentText(result);
                }
                alert.showAndWait();
            });

            node = getNodeFromGridPane(gridPane , 6 , i); //sell
            assert node != null;
            ((Button) node).setOnAction(actionEvent -> {
                int amount = (int) Math.floor(slider.getValue());
                String result = controller.sell(type , amount);
                Alert alert;
                if (result.equals("Sell successful!")) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Sell Successful");
                    alert.setContentText(type.getName() + " Sold Successfully!");
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Sell Failed");
                    alert.setContentText(result);
                }
                alert.showAndWait();
            });

            i++;
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
}
