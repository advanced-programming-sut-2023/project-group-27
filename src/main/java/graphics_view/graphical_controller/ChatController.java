package graphics_view.graphical_controller;

import controller.controller.CoreChatMenuController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.PrivateChat;
import model.chat.Reactions;

import java.util.ArrayList;

public class ChatController {
    private final CoreChatMenuController controller;
    @FXML
    public VBox privateChatList;
    @FXML
    private VBox messageList;
    @FXML
    private Tab publicChat;
    @FXML
    private Tab room;
    @FXML
    private Tab privateChats;
    @FXML
    private TextField inputMessage;
    @FXML
    private TextField searchUsername;

    public ChatController() {
        controller = new CoreChatMenuController();
        StrongholdCrusader.setLoggedInUser(new User("mazdak", ""
                , "mazmz", "", "", "", ""));
        StrongholdCrusader.addUser(new User("mani", "",
                "", "manman", "", "", ""));
        //TODO remove dummy user
    }

    public void initialize() {
        if (privateChats.isSelected()) {
            showPrivateChatList();
        }
    }

    public void sendMessagePublicChat(MouseEvent mouseEvent) {
        String message = inputMessage.getText();
        inputMessage.setText("");
        showMessage(controller.sendMessagePublicChat(message));
    }

    private void showMessage(Message message) {
        HBox showMessage = new HBox();
        showMessage.setSpacing(10);
        ImageView avatar = new ImageView(message.getSender().getAvatar());
        avatar.setFitHeight(15);
        avatar.setFitWidth(15);
        Text nickname = new Text(message.getSender().getNickname());
        nickname.setFill(Color.BLUE);
        Text content = new Text(message.getContent());
        Text date = new Text(message.getDate());
        ArrayList<Reactions> reactions = message.getReactions();
        showMessage.getChildren().add(avatar);
        showMessage.getChildren().add(nickname);
        showMessage.getChildren().add(content);
        showMessage.getChildren().add(date);
        for (Reactions reactions1 : reactions) {
            ImageView imageView = new ImageView(reactions1.getImage());
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            showMessage.getChildren().add(imageView);
        }
        messageList.getChildren().add(showMessage);
        //TODO implement showing message
    }

    public void startNewPrivateChat(MouseEvent mouseEvent) {
        String username = searchUsername.getText();
        String result = controller.startNewPrivateChat(username);
        Alert alert;
        if (result == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Start chat error!");
            alert.setContentText("User not found");
            alert.showAndWait();
            return;
        }
        if (result.equals("Private chat already exists")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Start chat error!");
            alert.setContentText(result);
            alert.showAndWait();
            return;
        }
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Start chat successful");
        alert.setContentText(result);
        alert.showAndWait();
        showPrivateChatList();
    }

    private void showPrivateChatList() {

    }
}
