package graphics_view.graphical_controller;

import controller.controller.CoreTradeMenuController;
import controller.controller.Utilities;
import graphics_view.view.TradeMenu;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Match;
import model.Trade;
import model.TradingSystem;
import model.User;

public class ManageTradeController {
    public VBox incomingVBox;
    public VBox outgoingVBox;
    private User loggedInUser;
    private Match match;
    private CoreTradeMenuController controller;

    public void init(User loggedInUser, Match match) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("new incoming trades:");
        alert.setContentText(loggedInUser.getMonarchy().getTradingSystem().notifications());
        alert.showAndWait();
        this.loggedInUser = loggedInUser;
        this.match = match;
        this.controller = new CoreTradeMenuController(match, loggedInUser, null);
        TradingSystem tradingSystem = loggedInUser.getMonarchy().getTradingSystem();

        for (Trade trade : tradingSystem.getIncomingTrades()) {
            HBox hBox = new HBox();
            hBox.setBorder(new javafx.scene.layout.Border(
                    new javafx.scene.layout.BorderStroke(Color.BLACK,
                            javafx.scene.layout.BorderStrokeStyle.SOLID,
                            new javafx.scene.layout.CornerRadii(20),
                            new javafx.scene.layout.BorderWidths(2))));
            hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            hBox.setMinHeight(50);
            Label label = new Label(trade.getOwner().getUsername()
                    + " wants to trade " + trade.getAmount() + " " + trade.getType().getName()
                    + " for " + trade.getPrice() + "$          ");
            label.setMaxWidth(300);
            label.setMinWidth(300);
            Label label1 = new Label("State: " + trade.getState() + "          ");
            label1.setMaxWidth(150);
            label1.setMinWidth(150);
            Label label2 = new Label("he says " + trade.getComment() + "          ");
            label2.setMaxWidth(300);
            label2.setMinWidth(300);
            Button buttonAccept = new Button("Accept");
            Button buttonReject = new Button("Reject");
            buttonAccept.setBackground(new javafx.scene.layout.Background(
                    new javafx.scene.layout.BackgroundFill(Color.GREEN,
                            new javafx.scene.layout.CornerRadii(20), null)));
            buttonAccept.setOnAction(event -> {
                controller.acceptTrade(String.valueOf(trade.getId()), "accept");
                hBox.setBackground(new javafx.scene.layout.Background(
                        new javafx.scene.layout.BackgroundFill(Color.GREEN,
                                new javafx.scene.layout.CornerRadii(20), null)));
                hBox.getChildren().remove(buttonAccept);
                hBox.getChildren().remove(buttonReject);
                label1.setText("State: done");
            });
            buttonReject.setBackground(new javafx.scene.layout.Background(
                    new javafx.scene.layout.BackgroundFill(Color.RED,
                            new javafx.scene.layout.CornerRadii(20), null)));
            buttonReject.setOnAction(event -> {
                trade.getOwner().getMonarchy().getTradingSystem().getOutgoingTrades().remove(trade);
                tradingSystem.getIncomingTrades().remove(trade);
                hBox.setBackground(new javafx.scene.layout.Background(
                        new javafx.scene.layout.BackgroundFill(Color.RED,
                                new javafx.scene.layout.CornerRadii(20), null)));
                hBox.getChildren().remove(buttonAccept);
                hBox.getChildren().remove(buttonReject);
                trade.setStateToRejected();
                label1.setText("State: " + trade.getState());
            });
            hBox.getChildren().add(label);
            hBox.getChildren().add(label1);
            hBox.getChildren().add(label2);
            if (trade.getState().equals("awaiting")){
                hBox.getChildren().add(buttonAccept);
                hBox.getChildren().add(buttonReject);
            }

            Color color = (trade.getState().equals("done")) ? Color.GREEN : (trade.getState().equals("rejected")) ? Color.RED : Color.YELLOW;
            hBox.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(color,
                    new javafx.scene.layout.CornerRadii(20), null)));
            incomingVBox.getChildren().add(hBox);
        }

        for (Trade trade : tradingSystem.getOutgoingTrades()) {
            HBox hBox = new HBox();
            hBox.setMinHeight(50);
            hBox.setSpacing(20);
            hBox.setAlignment(Pos.CENTER);
            hBox.setBorder(new javafx.scene.layout.Border(
                    new javafx.scene.layout.BorderStroke(Color.BLACK,
                            javafx.scene.layout.BorderStrokeStyle.SOLID,
                            new javafx.scene.layout.CornerRadii(20),
                            new javafx.scene.layout.BorderWidths(2))));
            Label label = new Label("You requested " + trade.getTarget().getUsername()
                    + " for " + trade.getAmount() + " " + trade.getType().getName()
                    + " in exchange of " + trade.getPrice() + "$");
            label.setMaxWidth(300);
            label.setMinWidth(300);
            Label label1 = new Label("State: " + trade.getState());
            label1.setMaxWidth(150);
            label1.setMinWidth(150);
            Label label2 = new Label("you said " + trade.getComment());
            label2.setMaxWidth(300);
            label2.setMinWidth(300);
            hBox.getChildren().add(label);
            hBox.getChildren().add(label1);
            hBox.getChildren().add(label2);
            Color color = (trade.getState().equals("done")) ? Color.GREEN : (trade.getState().equals("rejected")) ? Color.RED : Color.YELLOW;
            hBox.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(color,
                    new javafx.scene.layout.CornerRadii(20), null)));
            outgoingVBox.getChildren().add(hBox);
        }
    }

    public void back(ActionEvent event) throws Exception {
        new TradeMenu(loggedInUser, match).start(TradeMenu.getStage());
    }
}
