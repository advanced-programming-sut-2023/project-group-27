package model;

import controller.controller.Utilities;
import graphics_view.view.ProfileMenu;
import javafx.scene.image.Image;

public class User implements Comparable<User>{
    private String username;
    private String nickname;
    private String password;
    private int highScore;
    private String slogan;
    private String email;
    private String securityQ;
    private String securityA;
    private Monarchy monarchy;
    private boolean stayLoggedIn;
    private String imagePath;

    public User(String username, String password, String nickname, String slogan, String email, String securityQ, String securityA) {
        this.username = username;
        this.password = controller.controller.Utilities.encryptString(password);
        this.nickname = nickname;
        this.highScore = 0;
        this.slogan = slogan;
        this.email = email;
        this.securityQ = securityQ;
        this.securityA = Utilities.encryptString(securityA);
        stayLoggedIn = false;
        imagePath = "/assets/avatars/guest.jpg";
    }

    public int getHighScore() {
        return highScore;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSecurityA() {
        return securityA;
    }

    public Monarchy getMonarchy() {
        return monarchy;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = controller.controller.Utilities.encryptString(password);
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setSecurityA(String securityA) {
        this.securityA = Utilities.encryptString(securityA);
    }

    public boolean isPasswordCorrect(String password) {
        String encryptedPassword = controller.controller.Utilities.encryptString(password);
        return this.password.equals(encryptedPassword);
    }

    public void setMonarchy(Monarchy monarchy) {
        this.monarchy = monarchy;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    @Override
    public int compareTo(User o) {
        if (o.highScore != this.highScore){
            return o.highScore - this.highScore;
        }
        return this.username.compareTo(o.username);
    }

    public Image getAvatar() {
        return new Image(ProfileMenu.class.getResource(imagePath).toExternalForm());
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}