package controller.view_controllers;

import model.User;

import java.util.regex.Matcher;

public class ProfileMenuController {

    User loggedInUser;
    public String changeUsername(Matcher matcher) {
        return null;
    }

    public String changeNickname(Matcher matcher) {
        return null;
    }

    public boolean isPasswordValid(Matcher matcher) {
        return true;
    }

    public String changePassword(Matcher matcher) {
        return null;
    }

    public String changeSlogan(Matcher matcher) {
        return null;
    }

    public void removeSlogan() {

    }

    public int showHighScore() {
        return 1;
    }

    public int showRank() {
        return 1;
    }

    public String showSlogan() {
        return null;
    }

    public String showProfile() {
        return null;
    }
}
