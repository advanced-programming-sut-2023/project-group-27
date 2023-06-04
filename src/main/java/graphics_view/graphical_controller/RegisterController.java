package graphics_view.graphical_controller;

import controller.controller.CoreRegisterMenuController;
import controller.controller.Utilities;
import graphics_view.view.SecurityQuestionMenu;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class RegisterController {
    public TextField username;
    public TextField email;
    public TextField nickname;
    public TextField slogan;
    public PasswordField maskedPassword;
    public TextField unmaskedPassword;
    public CheckBox showPass;
    public Label passwordError;
    public CheckBox activateSlogan;
    public Button randomSlogan;
    private final CoreRegisterMenuController controller;

    public RegisterController() {
        controller = new CoreRegisterMenuController(null);
    }

    @FXML
    public void initialize() {
        showPass.setFont(new Font("System Bold", 12));
        activateSlogan.setFont(new Font("System Bold", 12));
        maskedPassword.visibleProperty().bind(showPass.selectedProperty().not());
        unmaskedPassword.visibleProperty().bind(showPass.selectedProperty());
        maskedPassword.managedProperty().bind(showPass.selectedProperty().not());
        unmaskedPassword.managedProperty().bind(showPass.selectedProperty());
        maskedPassword.textProperty().bindBidirectional(unmaskedPassword.textProperty());
        maskedPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utilities.validatePassword(newValue) != null){
                passwordError.setText(Utilities.validatePassword(newValue));
                passwordError.setManaged(true);
                passwordError.setVisible(true);
            } else {
                passwordError.setText("");
                passwordError.setManaged(false);
                passwordError.setVisible(false);
            }
        });
        slogan.managedProperty().bind(activateSlogan.selectedProperty());
        slogan.visibleProperty().bind(activateSlogan.selectedProperty());
        randomSlogan.managedProperty().bind(activateSlogan.selectedProperty());
        randomSlogan.visibleProperty().bind(activateSlogan.selectedProperty());
    }

    public void randomPassword(MouseEvent mouseEvent) {
        maskedPassword.setText(Utilities.randomPassword());
    }

    public void randomSlogan(MouseEvent mouseEvent) {
        slogan.setText(Utilities.randomSlogan());
    }

    public void register(MouseEvent mouseEvent) {
        String output = controller.initializeUser(
                username.getText(), maskedPassword.getText(), email.getText(), nickname.getText(),
                activateSlogan.isSelected() ? slogan.getText() : null);
        if (output != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(output);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("User created successfully!");
            alert.showAndWait();
            SecurityQuestionMenu securityQuestionMenu = new SecurityQuestionMenu(controller);
            try {
                securityQuestionMenu.start(Utilities.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
