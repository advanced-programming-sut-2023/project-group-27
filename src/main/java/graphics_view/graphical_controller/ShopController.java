package graphics_view.graphical_controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.GoodsType;
import model.StrongholdCrusader;
import model.User;

import javax.swing.text.Element;
import java.awt.*;

public class ShopController {
    @FXML
    private GridPane gridPane;
    private final User currentUser = StrongholdCrusader.getCurrentUser();

    public void initialize() {
        int i = 1;
        for (GoodsType type : GoodsType.values()) {
            Node node1 = getNodeFromGridPane(gridPane, 0, i);
            assert node1 != null;
            ((Label) node1).setText(type.getName());
            Node node2 = getNodeFromGridPane(gridPane , 2 , i);
            assert node2 != null;
            ((Label) node2).setText(Integer.toString(type.getShopBuyPrice()));
            Node node3 = getNodeFromGridPane(gridPane , 3 , i);
            assert node3 != null;
            ((Label) node3).setText(Integer.toString(type.getShopSellPrice()));
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
