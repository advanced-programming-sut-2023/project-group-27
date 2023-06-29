package graphics_view.graphical_controller;

import controller.controller.CoreChatMenuController;
import controller.controller.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    private HBox inputBox;
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
    private static Button sendButton;
    @FXML
    private TextField searchUsername;
    private boolean editMode = false;

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
            Messenger.setCurrentChat(Messenger.getPublicChat());
            initCurrentChat(messageList);
            initPrivateChat();
        }
    }

    private void initPrivateChat() {
        for (PrivateChat privateChat : Messenger.getUsersPrivateChats(
                StrongholdCrusader.getLoggedInUser())) {
            addPrivateChat(privateChat);
        }
    }

    static void initCurrentChat(VBox messageList) {
        for (Message message : Messenger.getCurrentChat().getAllMessages()) {
            showMessage(message , messageList);
        }
    }

    public void sendMessagePublicChat(MouseEvent mouseEvent) {
        Messenger.setCurrentChat(Messenger.getPublicChat());
        String message = inputMessage.getText();
        if (message.equals("")) return;
        inputMessage.setText("");
        showMessage(controller.sendMessage(message), messageList);
    }

    static void showMessage(Message message, VBox messageList) {
        HBox showMessage = new HBox();
        showMessage.setSpacing(50);
        HBox options = new HBox();
        setupOptions(options , message);
        HBox text = new HBox();
        setupText(text , message);
        Region filler = new Region();
        filler.setMaxWidth(200);
        filler.prefWidth(200);
        HBox.setHgrow(filler , Priority.ALWAYS);
        showMessage.getChildren().addAll(text , filler , options);
        messageList.getChildren().add(showMessage);
    }

    private static void setupText(HBox text , Message message) {
        text.setSpacing(10);
        ImageView avatar = new ImageView(message.getSender().getAvatar());
        avatar.setFitHeight(15);
        avatar.setFitWidth(15);
        Text nickname = new Text(message.getSender().getNickname());
        nickname.setFill(Color.BLUE);
        Text content = new Text(message.getContent());
        Text date = new Text(message.getDate());
        ArrayList<Reactions> reactions = message.getReactions();
        text.getChildren().add(avatar);
        text.getChildren().add(nickname);
        text.getChildren().add(content);
        text.getChildren().add(date);
        for (Reactions reactions1 : reactions) {
            ImageView imageView = new ImageView(reactions1.getImage());
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            text.getChildren().add(imageView);
        }
    }

    private static void setupOptions(HBox options , Message message) {
        options.setSpacing(10);
        options.setAlignment(Pos.BASELINE_RIGHT);
        Button reaction = new Button("Reactions");
        reaction.setOnAction(actionEvent -> newReaction(message));
        Button edit = new Button("Edit");
        reaction.setOnAction(actionEvent -> editMessage(message));
        Button delete = new Button("Delete");
        reaction.setOnAction(actionEvent -> deleteMessage(message));
        options.getChildren().add(reaction);
        options.getChildren().add(edit);
        options.getChildren().add(delete);
    }

    private static void deleteMessage(Message message) {

    }

    private static void editMessage(Message message) {

    }

    private static void newReaction(Message message) {

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
        Messenger.setCurrentChat(pv);
        Stage stage = Utilities.getStage();
        BorderPane borderPane = FXMLLoader.load(
                ChatController.class.getResource("/fxml/PrivateChat.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
