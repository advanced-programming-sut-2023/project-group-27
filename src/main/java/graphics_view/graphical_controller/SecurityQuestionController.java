package graphics_view.graphical_controller;

import controller.controller.CoreRegisterMenuController;
import controller.controller.Utilities;
import graphics_view.view.InitialMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.StrongholdCrusader;

public class SecurityQuestionController {
    public ListView securityQuestions;
    private CoreRegisterMenuController controller;
    public TextField securityAnswer;

    public SecurityQuestionController() {
        controller = new CoreRegisterMenuController(null);
    }

    @FXML
    public void initialize() {
        for (String securityQuestion : StrongholdCrusader.getSecurityQuestions()) {
            securityQuestions.getItems().add(securityQuestion);
        }
        securityQuestions.setMaxHeight(StrongholdCrusader.getSecurityQuestions().size() * 24 + 2);
    }

    public void setController(CoreRegisterMenuController controller) {
        this.controller = controller;
    }

    public void register(MouseEvent mouseEvent) {
        String output = controller.finalizeUser(securityQuestions.getSelectionModel()
                .getSelectedItem().toString(), securityAnswer.getText());
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
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new InitialMenu().start(Utilities.getStage());
    }
}
