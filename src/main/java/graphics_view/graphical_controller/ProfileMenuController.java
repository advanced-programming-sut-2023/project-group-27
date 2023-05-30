package graphics_view.graphical_controller;

import controller.controller.CoreProfileMenuController;
import graphics_view.view.Captcha;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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
    private TextField emailField;
    @FXML
    private Label nicknameEvaluation;
    @FXML
    private TextField nicknameField;
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

    private TextField currentPassword;
    private Label newPasswordEvaluation;
    private PasswordField newPassword;
    private Captcha captcha;
    private Rectangle captchaRectangle;
    private TextField captchaValue;
    private Button resetCaptcha;
    private Stage stage;
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

        usernameField.textProperty().addListener((observableValue, s, t1) -> {
            if (usernameField.getText().isEmpty())
                usernameEvaluation.setText("");
            else {
                usernameEvaluation.setText(controller.evaluateUsername(usernameField.getText()));
                usernameEvaluation.setTextFill(Color.RED);
                if (usernameEvaluation.getText().equals("okay!"))
                    usernameEvaluation.setTextFill(Color.GREEN);
            }
        });

        sloganField.textProperty().addListener((observableValue, s, t1) -> {
            if (!sloganField.getText().isEmpty()) {
                sloganEvaluation.setText("okay!");
                sloganEvaluation.setTextFill(Color.GREEN);
            }
        });

        nicknameField.textProperty().addListener((observableValue, s, t1) -> {
            if (!nicknameField.getText().isEmpty()) {
                nicknameEvaluation.setText("okay!");
                nicknameEvaluation.setTextFill(Color.GREEN);
            }
        });

        emailField.textProperty().addListener((observableValue, s, t1) -> {
            if (emailField.getText().isEmpty())
                emailEvaluation.setText("");
            else {
                emailEvaluation.setText(controller.evaluateEmail(emailField.getText()));
                emailEvaluation.setTextFill(Color.RED);
                if (emailEvaluation.getText().equals("okay!"))
                    emailEvaluation.setTextFill(Color.GREEN);
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
        controller.removeSlogan();

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


    public void changeNickName(MouseEvent mouseEvent) {
        if (!nicknameEvaluation.getText().equals("okay!"))
            return;

        controller.changeNickname(nicknameField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Nickname Changed Successfully!");
        alert.showAndWait();
        nicknameField.clear();
    }

    public void changeEmail(MouseEvent mouseEvent) {
        if (!emailEvaluation.getText().equals("okay!"))
            return;

        controller.changeEmail(emailField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Email Changed Successfully!");
        alert.showAndWait();
        emailField.clear();
    }

    public void showNewPasswordDialog(MouseEvent mouseEvent) {
        Scene scene = new Scene(getNewPasswordFields());
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("password change");
        stage.show();
    }

    private VBox getNewPasswordFields() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);
        currentPassword = new PasswordField();
        currentPassword.setPromptText("current password");
        VBox vBox1 = new VBox();
        newPasswordEvaluation = new Label();
        newPasswordEvaluation.setStyle("-fx-font-size: x-small");
        newPasswordEvaluation.setWrapText(true);
        newPasswordEvaluation.setMinHeight(30);
        newPassword = new PasswordField();
        newPassword.setPromptText("new password");
        vBox1.getChildren().addAll(newPasswordEvaluation, newPassword);

        captcha = new Captcha();
        captchaRectangle = new Rectangle(150, 50);
        captchaRectangle.setFill(new ImagePattern(captcha.getCaptcha()));
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        captchaValue = new TextField();
        captchaValue.setPromptText("captcha");
        resetCaptcha = new Button("reset");
        hBox.getChildren().addAll(captchaValue, resetCaptcha);

        vBox.getChildren().addAll(currentPassword, vBox1, captchaRectangle, hBox);
        mountCurrentPasswordListener();
        setResetCaptchaEvent();
        addButtons(vBox);
        return vBox;
    }

    private void addButtons(VBox vBox) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.getChildren().add(hBox);

        Button change = new Button("change");
        Button exit = new Button("exit");

        hBox.getChildren().addAll(exit, change);

        exit.setOnMouseClicked(mouseEvent -> stage.close());

        change.setOnMouseClicked(mouseEvent -> changePassword());
    }

    private void changePassword() {
        if (!newPasswordEvaluation.getText().equals("okay!")) return;

        if (!captchaValue.getText().equals(captcha.getValue())) {
            resetCaptchaPicture();
            return;
        }

        if (!controller.isPasswordValid(currentPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Incorrect password");
            currentPassword.clear();
            resetCaptchaPicture();
            alert.showAndWait();
            return;
        }

        controller.changePassword(newPassword.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("password changed successfully!");
        stage.close();
        alert.showAndWait();
    }

    private void resetCaptchaPicture() {
        captcha.refresh();
        captchaValue.clear();
        captchaRectangle.setFill(new ImagePattern(captcha.getCaptcha()));
    }

    private void setResetCaptchaEvent() {
        resetCaptcha.setOnMouseClicked(mouseEvent -> resetCaptchaPicture());
    }

    private void mountCurrentPasswordListener() {
        newPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (newPassword.getText().isEmpty()) {
                    newPasswordEvaluation.setText("");
                    return;
                }

                newPasswordEvaluation.setText(controller.evaluatePassword(newPassword.getText()));
                newPasswordEvaluation.setTextFill(Color.RED);
                if (newPasswordEvaluation.getText().equals("okay!"))
                    newPasswordEvaluation.setTextFill(Color.GREEN);

            }
        });
    }
}