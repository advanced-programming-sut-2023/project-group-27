package graphics_view.graphical_controller;

import controller.controller.CoreLoginMenuController;
import controller.controller.Utilities;
import graphics_view.view.Captcha;
import graphics_view.view.InitialMenu;
import graphics_view.view.LoginMenu;
import graphics_view.view.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;
import server.Connection;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text errorText;
    @FXML
    private Text errorTextFP;
    @FXML
    private Text sequrityQ;
    @FXML
    private TextField sequrityA;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    private Stage stage;
    private Scene scene;
    private boolean stayLoggedIn;
    private boolean userValid;
    private boolean answerValid;
    private Captcha captcha;
    private Rectangle captchaRectangle;
    private TextField captchaValue;
    private Button resetCaptcha;
    private boolean captchaComplete;
    private final CoreLoginMenuController controller;

    public LoginController() {
        controller = new CoreLoginMenuController(null);
        stayLoggedIn = false;
        userValid = false;
        answerValid = false;
        captchaComplete = false;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        if (this.stage == null) System.out.println("null stage");
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        if (this.scene == null) System.out.println("null scene");
    }

    public void login() throws Exception {
        showCaptcha();
        if (!captchaComplete) {
            errorText.setText("Please Complete Captcha");
            errorText.setFill(Color.WHITE);
        }
    }

    private void showCaptcha() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        captcha = new Captcha();
        captchaRectangle = new Rectangle(150 , 50);
        captchaRectangle.setFill(new ImagePattern(captcha.getCaptcha()));
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        captchaValue = new TextField();
        captchaValue.setPromptText("captcha");
        resetCaptcha = new Button("reset");
        hBox.getChildren().addAll(captchaValue, resetCaptcha);
        Button login = new Button("Login");
        stage = new Stage();
        login.setOnAction(actionEvent -> {
            if (!captchaValue.getText().equals(captcha.getValue())) {
                resetCaptchaPicture();
            }
            else {
                captchaComplete = true;
                stage.close();
                try {
                    continueLogin();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        vBox.getChildren().addAll(captchaRectangle , hBox , login);
        vBox.setAlignment(Pos.CENTER);
        setResetCaptchaEvent();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void continueLogin() throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        String result = controller.login(username, password, stayLoggedIn);
        if (result.equals("User logged in successfully!")) {
            Socket socket1 = new Socket("localhost", 8080);
            Socket socket2 = new Socket("localhost", 8080);
            Connection connection = new Connection(socket1, socket2);
            Utilities.setMainServer(connection);
            connection.response(StrongholdCrusader.getLoggedInUser().getUsername());
            new MainMenu().start(Utilities.getStage());
        } else {
            errorText.setText(result);
            errorText.setFill(Color.WHITE);
        }
    }

    public void forgotPassword() throws IOException {
        URL url = getClass().getResource("/fxml/ForgetPassword.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene forgetPasswordScene = new Scene(pane);
        stage.setScene(forgetPasswordScene);
        stage.show();
    }

    public void reset() {
        username.setText("");
        password.setText("");
    }

    public void stayLoggedIn() {
        stayLoggedIn = !stayLoggedIn;
    }

    public void checkUsername() {
        String username = this.username.getText();
        errorTextFP.setFill(Color.WHITE);
        if (!StrongholdCrusader.getAllUsers().containsKey(username))
            errorTextFP.setText("This username does not exist!");
        else {
            errorTextFP.setText("");
            userValid = true;
            User user = StrongholdCrusader.getUserByName(username);
            sequrityQ.setFill(Color.WHITE);
            sequrityQ.setText(user.getSecurityQ());
        }
    }

    public void checkAnswer() {
        errorTextFP.setFill(Color.WHITE);
        if (!userValid) {
            errorTextFP.setText("Please Fill The Field Above!");
            return;
        }
        User user = StrongholdCrusader.getUserByName(username.getText());
        if (!Utilities.encryptString(sequrityA.getText()).
                equals(user.getSecurityA())) {
            errorTextFP.setText("Wrong answer!");
        } else {
            answerValid = true;
            errorTextFP.setText("");
        }
    }

    public void changePassword() throws Exception {
        if (!answerValid) {
            errorTextFP.setText("Please Fill The Field Above!");
            return;
        }
        if (!newPassword.getText().equals(confirmPassword.getText())) {
            errorTextFP.setText("The confirmation does not match the password!");
        } else {
            User user = StrongholdCrusader.getUserByName(username.getText());
            String result = controller.forgetPassword(
                    user, newPassword.getText());
            if (!result.equals("Password changed successfully!")) {
                errorTextFP.setText(result);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Password Change");
            alert.setContentText("Password changed successfully!");
            alert.showAndWait();
            new LoginMenu().start(stage);
        }
    }

    public void back() throws Exception {
        new LoginMenu().start(Utilities.getStage());
    }

    public void exit() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) controller.exit();
    }

    private void resetCaptchaPicture() {
        captcha.refresh();
        captchaValue.clear();
        captchaRectangle.setFill(new ImagePattern(captcha.getCaptcha()));
    }

    private void setResetCaptchaEvent() {
        resetCaptcha.setOnMouseClicked(mouseEvent -> resetCaptchaPicture());
    }




    public void backToInitialMenu() throws Exception {
        new InitialMenu().start(Utilities.getStage());
    }
}
