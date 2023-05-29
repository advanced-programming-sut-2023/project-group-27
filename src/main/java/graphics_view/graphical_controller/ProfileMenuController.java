package graphics_view.graphical_controller;

import controller.controller.CoreProfileMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.StrongholdCrusader;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenuController implements Initializable {
    @FXML
    private Tab profileTab;
    @FXML
    private Label usernameEvaluation;
    @FXML
    private TextField usernameField;
    @FXML
    private Label sloganEvaluation;
    @FXML
    private TextField sloganField;
    @FXML
    private Label emailEvaluation;
    @FXML
    private TextField emailEvaluationText;
    @FXML
    private Label nicknameEvaluation;
    @FXML
    private TextField nicknameEvaluationText;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nicknameLabel;
    @FXML
    private Label sloganLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TabPane mainTabPane;


    private User currentUser = new User(
            "arshia", "Arshia@1", "arshi", "yoyo", "a@b.c", "dsa", "dsa");
    private CoreProfileMenuController controller;

    public void log(Event event) {
        if (mainTabPane.getSelectionModel().getSelectedItem() == profileTab) {
            usernameLabel.setText(currentUser.getUsername());
            nicknameLabel.setText(currentUser.getNickname());
            sloganLabel.setText((currentUser.getSlogan().isEmpty()) ? "no slogan!" : currentUser.getSlogan());
            emailLabel.setText(currentUser.getEmail());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StrongholdCrusader.setLoggedInUser(currentUser);
        controller = new CoreProfileMenuController(null);
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (usernameField.getText().isEmpty())
                    usernameEvaluation.setText("");
                else
                    usernameEvaluation.setText(controller.evaluateUsername(usernameField.getText()));
            }
        });

        sloganField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!sloganField.getText().isEmpty())
                    sloganEvaluation.setText("okay!");
            }
        });
    }

    public void changeUsername(MouseEvent mouseEvent) {
        if (!usernameEvaluation.getText().equals("okay!"))
            return;

        controller.changeUsername(usernameField.getText());
        usernameField.clear();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Username Changed Successfully!");
        alert.showAndWait();
    }

    public void clearSlogan(MouseEvent mouseEvent) {
        currentUser.setSlogan("");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Slogan Cleared Successfully!");
        alert.showAndWait();
        sloganField.clear();
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        if (!sloganEvaluation.getText().equals("okay!"))
            return;

        controller.changeSlogan(sloganField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Slogan Changed Successfully!");
        alert.showAndWait();
        sloganField.clear();
    }
}
