package graphics_view.graphical_controller;

import controller.controller.CoreLoginMenuController;
import controller.controller.Utilities;
import graphics_view.view.LoginMenu;
import graphics_view.view.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;

import java.io.IOException;
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
    private final CoreLoginMenuController controller;

    public LoginController() {
        controller = new CoreLoginMenuController(null);
        stayLoggedIn = false;
        userValid = false;
        answerValid = false;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        if (this.stage == null) System.out.println("null stage");
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        if (this.scene == null) System.out.println("null scene");
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        String result = controller.login(username, password, stayLoggedIn);
        if (result.equals("User logged in successfully!")) {
            new MainMenu().start(new Stage());
        } else {
            errorText.setText(result);
            errorText.setFill(Color.WHITE);
        }
    }

    public void forgotPassword(MouseEvent mouseEvent) throws IOException {
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

    public void stayLoggedIn(MouseEvent mouseEvent) {
        stayLoggedIn = !stayLoggedIn;
    }

    public void checkUsername(MouseEvent mouseEvent) {
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

    public void checkAnswer(MouseEvent mouseEvent) {
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

    public void changePassword(MouseEvent mouseEvent) throws Exception {
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

    public void back(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.getStage());
    }

    public void exit(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) controller.exit();
    }

    public void showCaptcha() {
        //TODO implement captcha
    }
}
