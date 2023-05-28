package graphics_view.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import model.User;

public class ProfileMenuController {
    @FXML
    private Tab profileTab;
    @FXML
    private Label usernameEvaluation;
    @FXML
    private TextField usernameEvaluationText;
    @FXML
    private Label sloganEvaluation;
    @FXML
    private TextField sloganEvaluationText;
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

    public void log(Event event) {
        if (mainTabPane.getSelectionModel().getSelectedItem() == profileTab) {
            usernameLabel.setText(currentUser.getUsername());
            nicknameLabel.setText(currentUser.getNickname());
            sloganLabel.setText(currentUser.getSlogan());
            emailLabel.setText(currentUser.getEmail());

        }
    }
}
