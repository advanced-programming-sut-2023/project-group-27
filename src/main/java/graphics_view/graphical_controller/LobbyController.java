package graphics_view.graphical_controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.Socket;

public class LobbyController {
    private Socket serverSocket;

    public Button createServer;
    public TextField searchTextField;

    public void createServer(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(getCreateServerBox()));
        stage.show();
    }

    public void search(MouseEvent mouseEvent) {
        String idToSearch = searchTextField.getText();
        //TODO send id to server
    }

    private VBox getCreateServerBox() {
        VBox vBox = new VBox();
        CheckBox isPublicCheckBox = new CheckBox("private");
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    if (Integer.parseInt(t1) > 8 || Integer.parseInt(t1) < 2)
                        textField.clear();
                }
                catch (Exception exception) {
                    textField.clear();
                }
            }
        });
        Label label = new Label("#server id");//Server id generated
        Button button = new Button("create");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText().equals(""))
                    return;
                boolean isPublic = !isPublicCheckBox.isSelected();
                String serverId = label.getText();
                int capacity = Integer.parseInt(textField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //TODO send to server
                alert.setTitle("confirmation");
                alert.setHeaderText("Server created");
                alert.setContentText(label.getText());
                alert.showAndWait();
            }
        });
        HBox hBox = new HBox(new Label("capacity"),textField);
        vBox.getChildren().addAll(isPublicCheckBox, hBox, label, button);
        return vBox;
    }

    public void refresh(MouseEvent mouseEvent) {
    }
}
