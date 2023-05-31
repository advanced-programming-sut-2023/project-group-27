package graphics_view.graphical_controller;

import controller.controller.CoreProfileMenuController;
import graphics_view.view.Captcha;
import graphics_view.view.ProfileMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.StrongholdCrusader;
import model.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class ProfileMenuController implements Initializable {
    @FXML
    private ScrollPane playerScrollBox;
    @FXML
    private Circle defaultAvatarToggle;
    @FXML
    private Tab scoreboardTab;
    @FXML
    private VBox playersVBox;
    @FXML
    private Label fileChooserLabel;
    @FXML
    private Label dropDestination;
    @FXML
    private Circle avatarCircle;
    @FXML
    private Tab profileTab;
    @FXML
    private Label usernameEvaluation;
    @FXML
    private TextField usernameField;
    @FXML
    private Label sloganEvaluation;
    @FXML
    private TextField sloganField;
    @FXML
    private Label emailEvaluation;
    @FXML
    private TextField emailField;
    @FXML
    private Label nicknameEvaluation;
    @FXML
    private TextField nicknameField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nicknameLabel;
    @FXML
    private Label sloganLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TabPane mainTabPane;

    private TextField currentPassword;
    private Label newPasswordEvaluation;
    private PasswordField newPassword;
    private Captcha captcha;
    private Rectangle captchaRectangle;
    private TextField captchaValue;
    private Button resetCaptcha;
    private Stage stage;
    private File currentFileTOBeApplied;
    private File currentFileTOBeApplied2;
    private Circle specialCircle;
    private int defaultAvatar = 1;
    private final int totalDefaultAvatars = 8;
    Object[] users;
    private User currentUser = new User(
            "arshia", "Arshia@1", "arshi", "yoyo", "a@b.c", "dsa", "dsa");
    private CoreProfileMenuController controller;
    private final Media media= new Media(ProfileMenu.class.getResource("/assets/tracks/profileMenu.mp3").toExternalForm());
    private final MediaPlayer mediaPlayer = new MediaPlayer(media);

    public void log(Event event) {
        if (mainTabPane.getSelectionModel().getSelectedItem() == profileTab) {
            usernameLabel.setText(currentUser.getUsername());
            nicknameLabel.setText(currentUser.getNickname());
            sloganLabel.setText((currentUser.getSlogan().isEmpty()) ? "no slogan!" : currentUser.getSlogan());
            emailLabel.setText(currentUser.getEmail());
            avatarCircle.setFill(new ImagePattern(currentUser.getAvatar()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StrongholdCrusader.setLoggedInUser(currentUser);
        currentUser.setHighScore(50);
        User user1 = new User(
                "alex", "Arshia@1", "ali", "yoyo", "a@b.c", "dsa", "dsa");
        user1.setHighScore(30);
        StrongholdCrusader.addUser(currentUser);
        StrongholdCrusader.addUser(user1);

        controller = new CoreProfileMenuController(null);
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.play();

        defaultAvatarToggle.setFill(new ImagePattern(new Image(ProfileMenu.class.getResource(
                "/assets/avatars/default/" + defaultAvatar + ".png").toExternalForm())));
        addDropFeature();

        usernameField.textProperty().addListener((observableValue, s, t1) -> {
            if (usernameField.getText().isEmpty())
                usernameEvaluation.setText("");
            else {
                usernameEvaluation.setText(controller.evaluateUsername(usernameField.getText()));
                usernameEvaluation.setTextFill(Color.RED);
                if (usernameEvaluation.getText().equals("okay!"))
                    usernameEvaluation.setTextFill(Color.GREEN);
            }
        });

        sloganField.textProperty().addListener((observableValue, s, t1) -> {
            if (!sloganField.getText().isEmpty()) {
                sloganEvaluation.setText("okay!");
                sloganEvaluation.setTextFill(Color.GREEN);
            }
        });

        nicknameField.textProperty().addListener((observableValue, s, t1) -> {
            if (!nicknameField.getText().isEmpty()) {
                nicknameEvaluation.setText("okay!");
                nicknameEvaluation.setTextFill(Color.GREEN);
            }
        });

        emailField.textProperty().addListener((observableValue, s, t1) -> {
            if (emailField.getText().isEmpty())
                emailEvaluation.setText("");
            else {
                emailEvaluation.setText(controller.evaluateEmail(emailField.getText()));
                emailEvaluation.setTextFill(Color.RED);
                if (emailEvaluation.getText().equals("okay!"))
                    emailEvaluation.setTextFill(Color.GREEN);
            }
        });

        playerScrollBox.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (t1.doubleValue() >= 0.9)
                    loadNextGroupOfPlayers();
            }
        });
    }

    private void loadNextGroupOfPlayers() {
        if (playersVBox.getChildren().size() == users.length)
            return;

        int size = playersVBox.getChildren().size();
        for (int index = size; index < size + Math.min(10, users.length - size); index++)
            playersVBox.getChildren().add(getPLayerDataHBox((User) users[index]));

    }

    public void changeUsername(MouseEvent mouseEvent) {
        if (!usernameEvaluation.getText().equals("okay!"))
            return;

        controller.changeUsername(usernameField.getText());
        usernameField.clear();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Username Changed Successfully!");
        alert.showAndWait();
    }

    public void clearSlogan(MouseEvent mouseEvent) {
        controller.removeSlogan();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Slogan Cleared Successfully!");
        alert.showAndWait();
        sloganField.clear();
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        if (!sloganEvaluation.getText().equals("okay!"))
            return;

        controller.changeSlogan(sloganField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Slogan Changed Successfully!");
        alert.showAndWait();
        sloganField.clear();
    }


    public void changeNickName(MouseEvent mouseEvent) {
        if (!nicknameEvaluation.getText().equals("okay!"))
            return;

        controller.changeNickname(nicknameField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Nickname Changed Successfully!");
        alert.showAndWait();
        nicknameField.clear();
    }

    public void changeEmail(MouseEvent mouseEvent) {
        if (!emailEvaluation.getText().equals("okay!"))
            return;

        controller.changeEmail(emailField.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Email Changed Successfully!");
        alert.showAndWait();
        emailField.clear();
    }

    public void showNewPasswordDialog(MouseEvent mouseEvent) {
        Scene scene = new Scene(getNewPasswordFields());
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("password change");
        stage.show();
    }

    private VBox getNewPasswordFields() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);
        currentPassword = new PasswordField();
        currentPassword.setPromptText("current password");
        VBox vBox1 = new VBox();
        newPasswordEvaluation = new Label();
        newPasswordEvaluation.setStyle("-fx-font-size: x-small");
        newPasswordEvaluation.setWrapText(true);
        newPasswordEvaluation.setMinHeight(30);
        newPassword = new PasswordField();
        newPassword.setPromptText("new password");
        vBox1.getChildren().addAll(newPasswordEvaluation, newPassword);

        captcha = new Captcha();
        captchaRectangle = new Rectangle(150, 50);
        captchaRectangle.setFill(new ImagePattern(captcha.getCaptcha()));
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        captchaValue = new TextField();
        captchaValue.setPromptText("captcha");
        resetCaptcha = new Button("reset");
        hBox.getChildren().addAll(captchaValue, resetCaptcha);

        vBox.getChildren().addAll(currentPassword, vBox1, captchaRectangle, hBox);
        mountCurrentPasswordListener();
        setResetCaptchaEvent();
        addButtons(vBox);
        return vBox;
    }

    private void addButtons(VBox vBox) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.getChildren().add(hBox);

        Button change = new Button("change");
        Button exit = new Button("exit");

        hBox.getChildren().addAll(exit, change);

        exit.setOnMouseClicked(mouseEvent -> stage.close());

        change.setOnMouseClicked(mouseEvent -> changePassword());
    }

    private void changePassword() {
        if (!newPasswordEvaluation.getText().equals("okay!")) return;

        if (!captchaValue.getText().equals(captcha.getValue())) {
            resetCaptchaPicture();
            return;
        }

        if (!controller.isPasswordValid(currentPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Incorrect password");
            currentPassword.clear();
            resetCaptchaPicture();
            alert.showAndWait();
            return;
        }

        controller.changePassword(newPassword.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("password changed successfully!");
        stage.close();
        alert.showAndWait();
    }

    private void resetCaptchaPicture() {
        captcha.refresh();
        captchaValue.clear();
        captchaRectangle.setFill(new ImagePattern(captcha.getCaptcha()));
    }

    private void setResetCaptchaEvent() {
        resetCaptcha.setOnMouseClicked(mouseEvent -> resetCaptchaPicture());
    }

    private void mountCurrentPasswordListener() {
        newPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (newPassword.getText().isEmpty()) {
                    newPasswordEvaluation.setText("");
                    return;
                }

                newPasswordEvaluation.setText(controller.evaluatePassword(newPassword.getText()));
                newPasswordEvaluation.setTextFill(Color.RED);
                if (newPasswordEvaluation.getText().equals("okay!"))
                    newPasswordEvaluation.setTextFill(Color.GREEN);

            }
        });
    }

    private void addDropFeature() {

        dropDestination.setOnDragOver(event -> {
            if (event.getGestureSource() != dropDestination
                    && event.getDragboard().hasFiles()
                    && event.getDragboard().getFiles().size() == 1
                    && event.getDragboard().getFiles().get(0).getName().matches(".*\\.((jpg)|(png))")) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        dropDestination.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {

                File[] files = dragEvent.getDragboard().getFiles().toArray(new File[0]);

                currentFileTOBeApplied2 = files[0];
                dropDestination.setText(currentFileTOBeApplied2.getName());
                dropDestination.setStyle("-fx-background-color: green");
                dragEvent.setDropCompleted(true);
            }
        });
    }

    public void chooseFile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png Files", "*.png")
                ,new FileChooser.ExtensionFilter("jpg Files", "*.jpg"));
        currentFileTOBeApplied = fileChooser.showOpenDialog(new Stage());
        if (currentFileTOBeApplied == null) return;
        fileChooserLabel.setText(currentFileTOBeApplied.getName());
    }

    public void handleDroppedFile(MouseEvent mouseEvent) {
        if (currentFileTOBeApplied2 == null) return;

        saveFileAndApply(currentFileTOBeApplied2);
        currentFileTOBeApplied2 = null;
        dropDestination.setText("drop here");
        dropDestination.setStyle("-fx-background-color: red");
        showSuccessPopUp();
    }

    public void handleChosenFile(MouseEvent mouseEvent) {
        if (currentFileTOBeApplied == null) return;

        saveFileAndApply(currentFileTOBeApplied);
        currentFileTOBeApplied = null;
        fileChooserLabel.setText("no file chosen");
        showSuccessPopUp();
    }

    private void showSuccessPopUp() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("avatar changed successfully!");
        alert.showAndWait();
    }

    private void saveFileAndApply(File file) {
        String noise = Integer.toString(new Random(23123).nextInt(10000)+ 1);
        File file1 = new File("./src/main/resources/assets/avatars/external/", noise + file.getName());
        File file2 = new File("./target/classes/assets/avatars/external/", noise + file.getName());
        try {
            file1.createNewFile();
            file2.createNewFile();
        } catch (IOException ignored) {
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageIO.write(bufferedImage, "jpg", file1);
            ImageIO.write(bufferedImage, "jpg", file2);
        } catch (Exception ignored) {
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageIO.write(bufferedImage, "png", file1);
            ImageIO.write(bufferedImage, "png", file2);
        } catch (Exception e) {
        }
        currentUser.setImagePath("/assets/avatars/external/" + noise + file.getName());
    }

    public void scoreboardTabLog(Event event) {
        if (mainTabPane.getSelectionModel().getSelectedItem() == scoreboardTab) {
            playersVBox.getChildren().clear();
            users = StrongholdCrusader.getAllSortedUsers();

            for (int index = 0; index < Math.min(10, users.length); index++)
                playersVBox.getChildren().add(getPLayerDataHBox((User) users[index]));
        }
    }

    private HBox getPLayerDataHBox(User user) {
        HBox hBox = new HBox();
        hBox.setStyle(
                "-fx-background-color: grey;\n" +
                "-fx-max-width: 400;\n" +
                "-fx-min-width: 400;\n" +
                "-fx-max-height: 110;\n" +
                "-fx-min-height: 110;\n" +
                "-fx-spacing: 20;\n" +
                "-fx-border-radius: 8;\n" +
                " -fx-background-radius: 8;");

        hBox.setAlignment(Pos.CENTER);
        Circle circle = new Circle(50);
        circle.setFill(new ImagePattern(user.getAvatar()));
        addProfileAdaptEvent(circle, user);

        if (user == currentUser) specialCircle = circle;

        Label label = new Label(user.getUsername());
        label.setStyle("-fx-font-size: xx-large");
        Label label2 = new Label(Integer.toString(user.getHighScore()));
        label2.setStyle("-fx-font-size: xx-large");
        label.setMinWidth(100);
        label.setMaxWidth(100);
        label2.setMinWidth(100);
        label2.setMaxWidth(100);
        hBox.getChildren().addAll(circle, label, label2);
        return hBox;
    }

    private void addProfileAdaptEvent(Circle circle, User user) {
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (currentUser == user) return;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Profile Avatar Change");
                alert.setHeaderText("Adapting this avatar");
                alert.setContentText("This avatar will be copied for you. okay?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    currentUser.setImagePath(user.getImagePath());

                    if (specialCircle != null)
                        specialCircle.setFill(new ImagePattern(currentUser.getAvatar()));
                }
            }
        });
    }

    public void toggleDefaultAvatar(MouseEvent mouseEvent) {
        defaultAvatar++;
        if (defaultAvatar == totalDefaultAvatars + 1) defaultAvatar = 1;
        defaultAvatarToggle.setFill(
                new ImagePattern(new Image(ProfileMenu.class.getResource(
                        "/assets/avatars/default/" + defaultAvatar + ".png").toExternalForm())));
    }

    public void handleDefaultAvatar(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Profile Avatar Change");
        alert.setHeaderText("Choosing Default Avatar");
        alert.setContentText("This will be your avatar!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK)
            currentUser.setImagePath("/assets/avatars/default/" + defaultAvatar + ".png");
    }
}