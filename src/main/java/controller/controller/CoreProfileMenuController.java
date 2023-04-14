package controller.controller;

import model.User;

public class CoreProfileMenuController {
    User loggedInUser;

    public String changeUsername(String username) {
        return null;
    }

    public String changeNickname(String nickname) {
        return null;
    }

    public boolean isPasswordValid(String oldPassword) {
        return loggedInUser.isPasswordCorrect(oldPassword);
    }

    public String changePassword(String newPassword, String newPasswordConfirmation) {
        return null;
    }

    public String changeSlogan(String newSlogan) {
        return null;
    }

    public void removeSlogan() {

    }

    public int showHighScore() {
        return loggedInUser.getHighScore();
    }

    public int showRank() {
        return 1;
    }

    public String showSlogan() {
        return loggedInUser.getSlogan();
    }

    public String showProfile() {
        return null;
    }
}
