package graphics_view.graphical_controller;

import controller.controller.CoreTradeMenuController;
import controller.controller.Utilities;
import graphics_view.view.TradeMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import model.GoodsType;
import model.User;

import javax.swing.*;
import java.awt.*;


public class TradeWithController {
    private model.User loggedInUser;
    private model.User user;
    private model.Match match;
    private GoodsType[] goodsTypes = GoodsType.values();
    private Label[] counts = new Label[goodsTypes.length];
    private CoreTradeMenuController controller;

    public void init(User loggedInUser, User user, model.Match match, BorderPane pane) {
        this.loggedInUser = loggedInUser;
        this.user = user;
        this.match = match;
        controller = new CoreTradeMenuController(match, loggedInUser, null);
        VBox vBox = new VBox();
        int i = 0;
        for (GoodsType goodsType : goodsTypes) {
            int index = i;
            HBox hBox = new HBox();
            hBox.setBackground(new Background(new BackgroundFill(Color.GOLDENROD,
                    new CornerRadii(20), null)));
            hBox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK,
                    BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT)));
            hBox.setMaxWidth(200);
            hBox.setMaxHeight(50);

            Label e = new Label(goodsType.getName());
            e.setMinWidth(100);
            hBox.getChildren().add(e);
            counts[i] = new Label("0");
            hBox.getChildren().add(counts[index]);
            Button plusButton = new Button("+");
            plusButton.setOnAction(event -> {
                counts[index].setText(Integer.parseInt(counts[index].getText()) + 1 + "");
                for (int j = 0; j < goodsTypes.length; j++) {
                    if (j != index) {
                        counts[j].setText("0");
                    }
                }
            });
            hBox.getChildren().add(plusButton);
            Button minusButton = new Button("-");
            minusButton.setOnAction(event -> {
                counts[index].setText(Math.max(Integer.parseInt(counts[index].getText()) - 1, 0) + "");
            });
            hBox.getChildren().add(minusButton);
            hBox.setAlignment(javafx.geometry.Pos.CENTER);
            hBox.setSpacing(10);
            vBox.getChildren().add(hBox);
            i++;
        }
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        pane.setCenter(vBox);

    }

    public void back(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
//        new TradeMenu(loggedInUser, match).start(controller.controller.Utilities.getStage());
    }

    public void next(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        GoodsType goodsType = null;
        int count = 0;
        for (int i = 0; i < goodsTypes.length; i++) {
            if (Integer.parseInt(counts[i].getText()) != 0) {
                goodsType = goodsTypes[i];
                count = Integer.parseInt(counts[i].getText());
                counts[i].setText("0");
                break;
            }
        }
        if (goodsType == null) {
            JOptionPane.showMessageDialog(null, "Please select a good type");
            return;
        }
        Popup popup = new Popup();
        TextField priceField = new TextField();
        priceField.setText("10");
        //set priceField measures
        priceField.setMaxWidth(250);
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        TextArea textArea = new TextArea();
        textArea.setText("Hello, " + user.getUsername());
        // set textArea measures
        textArea.setMaxWidth(250);
        textArea.setMaxHeight(50);
        GoodsType finalGoodsType = goodsType;
        int finalCount = count;
        Button button = new Button("Request");
        button.setOnAction(event -> {
            try {
                String output = controller.tradeRequest(
                        user.getUsername(), finalGoodsType.getName(),
                        String.valueOf(finalCount), priceField.getText(),
                        textArea.getText());
                popup.hide();
                if (output.equals("trade request sent\n")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Trade Request");
                    alert.setHeaderText("Trade Request");
                    alert.setContentText("Trade Request Sent Successfully");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Trade Request");
                    alert.setHeaderText("Trade Request");
                    alert.setContentText(output);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button button1 = new Button("Donate");
        button1.setOnAction(event -> {
            controller.tradeRequest(
                    user.getUsername(), finalGoodsType.getName(),
                    String.valueOf(finalCount), "0",
                    textArea.getText());
            popup.hide();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donation sent");
            alert.setHeaderText("Donation sent");
            alert.showAndWait();
        });
        Label label = new Label("Enter your price");
        label.setTextFill(Paint.valueOf("white"));
        label.setFont(new Font("Arial", 20));

        Label label1 = new Label("Enter your message");
        label1.setTextFill(Paint.valueOf("white"));
        label1.setFont(new Font("Arial", 20));

        VBox vBox = new VBox(label, priceField, label1, textArea, button, button1);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMinWidth(300);
        vBox.setMinHeight(300);
        vBox.setSpacing(20);
        BackgroundImage image = new BackgroundImage(new Image("file:src/main/resources/assets/tradeMenu/trading1.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false));
        Background background = new Background(image);
        vBox.setBackground(background);
        // set vBox background corner rounded
        vBox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT)));
        popup.getContent().add(vBox);

        popup.show(Utilities.getStage());
    }
}
