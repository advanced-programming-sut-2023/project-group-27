package graphics_view.graphical_controller;

import controller.controller.CoreChatMenuController;
import controller.controller.Utilities;
import graphics_view.view.ChatMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.Room;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomChatController implements Initializable {
    private final CoreChatMenuController controller;
    @FXML
    private Label roomName;
    @FXML
    private TextField searchUsername;
    @FXML
    private VBox messageList;
    @FXML
    private TextField inputMessage;
    private Room room;

    public RoomChatController() {
        controller = new CoreChatMenuController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChatController.initCurrentChat(messageList , inputMessage);
        roomName.setText(((Room) Messenger.getCurrentChat()).getRoomName());
        roomName.setTextFill(Color.GREEN);
        roomName.setStyle("-fx-font-size: 20");
        room = (Room) Messenger.getCurrentChat();
    }
    public void addUserToRoom(MouseEvent mouseEvent) {
        String username = searchUsername.getText();
        Alert alert;
        User user = StrongholdCrusader.getUserByName(username);
        if (user == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User Error");
            alert.setContentText("User not found!");
            alert.showAndWait();
            return;
        }
        if (room.getParticipantUserByName(username) != null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User Error");
            alert.setContentText("The user is already in room!");
            alert.showAndWait();
            searchUsername.setText("");
            return;
        }
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Add success");
        alert.setContentText(user.getUsername() +
                " Successfully added to room!");
        alert.showAndWait();
        searchUsername.setText("");
        room.addUserToChat(user);
        CoreChatMenuController.updateChat();
    }

    public void sendMessageRoomChat(MouseEvent mouseEvent) {
        String message = inputMessage.getText();
        inputMessage.setText("");
        showMessage(controller.sendMessage(message));
    }

    private void showMessage(Message message) {
        ChatController.showMessage(message , messageList , inputMessage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        Messenger.setCurrentChat(null);
        new ChatMenu().start(Utilities.getStage());
    }

}
