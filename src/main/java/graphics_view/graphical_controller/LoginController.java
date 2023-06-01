package graphics_view.graphical_controller;

import controller.controller.CoreLoginMenuController;
import graphics_view.view.MainMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text errorText;
    private boolean stayLoggedIn;
    private final CoreLoginMenuController controller;

    public LoginController() {
        controller = new CoreLoginMenuController(null);
        stayLoggedIn = false;
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        //TODO remove dummy add user
        StrongholdCrusader.addUser(new User("mazdak" , "Password" ,
                "maz" , "" , "" , "" ,""));


        String username = this.username.getText();
        String password = this.password.getText();
        String result = controller.login(username , password , stayLoggedIn);
        if (result.equals("User logged in successfully!")) {
            new MainMenu().start(new Stage());
        }
        else {
            errorText.setText(result);
            errorText.setFill(Color.WHITE);
        }
    }

    public void forgotPassword(MouseEvent mouseEvent) {

    }

    public void reset() {
        username.setText("");
        password.setText("");
    }

    public void stayLoggedIn(MouseEvent mouseEvent) {
        stayLoggedIn = true;
    }
}
