package graphics_view.graphical_controller;

import controller.controller.CoreRegisterMenuController;
import controller.controller.Utilities;
import graphics_view.view.Captcha;
import graphics_view.view.InitialMenu;
import graphics_view.view.SecurityQuestionMenu;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import javax.swing.*;

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

    public void randomPassword() {
        maskedPassword.setText(Utilities.randomPassword());
    }

    public void randomSlogan() {
        slogan.setText(Utilities.randomSlogan());
    }

    public void register() {
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
            Popup popup = new Popup();
            Pane pane = new Pane();
            VBox vBox = new VBox();
            // set pane background color
            pane.setStyle("-fx-background-color: rgba(150,136,136,0.5);");
            Captcha captcha = new Captcha();
            Rectangle rectangle = new Rectangle(120, 30);
            rectangle.setLayoutX(80);
            rectangle.setLayoutY(80);
            rectangle.setFill(new ImagePattern(captcha.getCaptcha()));
            TextField captchaValue = new TextField();
            captchaValue.setLayoutX(100);
            captchaValue.setLayoutY(300);
            Button submit = new Button("Submit");
            submit.setLayoutX(100);
            submit.setLayoutY(350);
            submit.setOnAction(event -> {
                popup.hide();
                if (captchaValue.getText().equals(captcha.getValue())) {
                    try {
                        new SecurityQuestionMenu(controller).start(Utilities.getStage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Captcha is wrong!");
                    alert.showAndWait();
                }
            });
            vBox.getChildren().addAll(rectangle, captchaValue, submit);
            vBox.setAlignment(javafx.geometry.Pos.CENTER);
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(20));
//            pane.setPadding(new Insets(20));
//            vBox.setLayoutY(100);
//            vBox.setLayoutX(100);
//            pane.setMinHeight(400);
//            pane.setMinWidth(300);
            pane.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK,
                    BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
            pane.getChildren().add(vBox);
            popup.getContent().add(pane);
            popup.show(Utilities.getStage());
        }
    }

    public void backToInitialMenu() throws Exception {
        new InitialMenu().start(Utilities.getStage());
    }
}
