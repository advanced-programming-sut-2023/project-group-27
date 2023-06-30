package graphics_view.graphical_controller;

import controller.controller.CoreChatMenuController;
import controller.controller.Utilities;
import graphics_view.view.ChatMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.PrivateChat;

import java.net.URL;
import java.util.ResourceBundle;

public class PrivateChatController implements Initializable {
    private final CoreChatMenuController controller;
    @FXML
    private VBox privateMessageList;
    @FXML
    private Text username;
    @FXML
    private TextField inputMessage;

    public PrivateChatController() {
        controller = new CoreChatMenuController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChatController.initCurrentChat(privateMessageList , inputMessage);
        username.setText(((PrivateChat) Messenger.getCurrentChat()).
                getOtherUser().getUsername());
        username.setFill(Color.GREEN);
        username.setStyle("-fx-font-size: 20");
    }

    public void sendMessagePrivateChat(MouseEvent mouseEvent) {
        String message = inputMessage.getText();
        inputMessage.setText("");
        showMessage(controller.sendMessage(message));
    }

    private void showMessage(Message message) {
        ChatController.showMessage(message, privateMessageList , inputMessage);
        //TODO implement showing message for other person
    }



    public void back(MouseEvent mouseEvent) throws Exception {
        Messenger.setCurrentChat(null);
        new ChatMenu().start(Utilities.getStage());
    }
}
