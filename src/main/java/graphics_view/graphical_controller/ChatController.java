package graphics_view.graphical_controller;

import controller.controller.CoreChatMenuController;
import controller.controller.Utilities;
import graphics_view.view.ChatMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.PrivateChat;
import model.chat.Reactions;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private final CoreChatMenuController controller;
    @FXML
    private TabPane mainTabPane;
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
    @FXML
    private Text username;
    private TabPane root;
    private Scene scene;

    public ChatController() {
        controller = new CoreChatMenuController();
        StrongholdCrusader.setLoggedInUser(new User("mazdak", ""
                , "mazmz", "", "", "", ""));
        StrongholdCrusader.addUser(new User("mani", "",
                "", "manman", "", "", ""));
        //TODO remove dummy user
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (publicChat != null) {
            initPublicChat();
            initPrivateChat();
        }
    }

    private void initPrivateChat() {
        for (PrivateChat privateChat : Messenger.getUsersPrivateChats(
                StrongholdCrusader.getLoggedInUser())) {
            addPrivateChat(privateChat);
        }
    }

    private void initPublicChat() {
        for (Message message : Messenger.getPublicChat().getAllMessages()) {
            showMessage(message);
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
        PrivateChat pv = Messenger.getPrivateChatByUsers(
                StrongholdCrusader.getUserByName(username) ,
                StrongholdCrusader.getLoggedInUser());
        addPrivateChat(pv);
    }

    private void addPrivateChat(PrivateChat pv) {
        HBox privateChat = new HBox();
        privateChat.setSpacing(10);
        ImageView avatar = new ImageView(pv.getOtherUser().getAvatar());
        avatar.setFitWidth(20);
        avatar.setFitHeight(20);
        Text otherUsername = new Text(pv.getOtherUser().getUsername());
        otherUsername.setFill(Color.BLUE);
        otherUsername.setStyle("-fx-font-size: 20");
        Button enterChat = new Button("Enter Chat");
        enterChat.setOnAction(actionEvent -> {
            try {
                enterPrivateChat(pv);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        privateChat.getChildren().add(avatar);
        privateChat.getChildren().add(otherUsername);
        privateChat.getChildren().add(enterChat);
        privateChatList.getChildren().add(privateChat);
    }

    private void enterPrivateChat(PrivateChat pv) throws IOException {
        Stage stage = Utilities.getStage();
        BorderPane borderPane = FXMLLoader.load(
                ChatController.class.getResource("/fxml/PrivateChat.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
//        System.out.println(username == null);
//        username.setText(pv.getOtherUser().getUsername());
        stage.show();
    }

    public void sendMessagePrivateChat(MouseEvent mouseEvent) {

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new ChatMenu().start(Utilities.getStage());
    }
}
