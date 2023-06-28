package graphics_view.graphical_controller;

import controller.controller.CoreChatMenuController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Reactions;

import java.util.ArrayList;

public class ChatController {
    private final CoreChatMenuController controller;
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

    public ChatController() {
        controller = new CoreChatMenuController();
        StrongholdCrusader.setLoggedInUser(new User("maz", ""
                , "mazmz", "", "", "", ""));
        //TODO remove dummy user
    }

    public void initialize() {
    }

    public void sendMessagePublicChat(MouseEvent mouseEvent) {
        String message = inputMessage.getText();
        showMessage(controller.sendMessagePublicChat(message));
    }

    public void showMessage(Message message) {
        HBox showMessage = new HBox();
        showMessage.setAlignment(Pos.CENTER);
        showMessage.setSpacing(10);
        ImageView avatar = new ImageView(message.getSender().getAvatar());
        avatar.setFitHeight(15);
        avatar.setFitWidth(15);
        System.out.println(message.getSender().getNickname());
        Text nickname = new Text(message.getSender().getNickname());
        Text content = new Text(message.getContent());
        ArrayList<Reactions> reactions = message.getReactions();
        showMessage.getChildren().add(avatar);
        showMessage.getChildren().add(nickname);
        showMessage.getChildren().add(content);
        for (Reactions reactions1 : reactions) {
            ImageView imageView = new ImageView(reactions1.getImage());
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            showMessage.getChildren().add(imageView);
        }
        messageList.getChildren().add(showMessage);
        //TODO implement showing message
    }
}
