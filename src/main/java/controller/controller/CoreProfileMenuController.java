package controller.controller;

import controller.view_controllers.ProfileMenuController;
import model.StrongholdCrusader;
import model.User;
import view.ProfileMenu;

import java.util.Scanner;

import static controller.controller.Utilities.validatePassword;

public class CoreProfileMenuController {
    User loggedInUser;
    private final ProfileMenu profileMenu;

    public CoreProfileMenuController(){
        profileMenu = new ProfileMenu(new ProfileMenuController());
    }
    public void run(Scanner scanner){
        profileMenu.run(scanner);
    }

    public String changeUsername(String username) {
        if (!username.matches("[0-9a-zA-Z_]+")) {
            return "Invalid username format";
        }
        StrongholdCrusader.getAllUsers().remove(loggedInUser.getUsername());
        loggedInUser.setUsername(username);
        StrongholdCrusader.getAllUsers().put(username, loggedInUser);
        return "Successful";
    }

    public void changeNickname(String nickname) {
        loggedInUser.setNickname(nickname);
    }

    public boolean isPasswordValid(String oldPassword) {
        return loggedInUser.isPasswordCorrect(oldPassword);
    }

    public String changePassword(String newPassword, String newPasswordConfirmation) {
        String error = validatePassword(newPassword);
        if (error != null) return error;
        if (newPassword.equals(loggedInUser.getPassword())) {
            return "Password can't be the same as old password";
        }
        if (newPassword.equals(loggedInUser.getUsername())) {
            return "Password can't be the same as username";
        }
        if (newPassword.equals(loggedInUser.getNickname())) {
            return "Password can't be the same as nickname";
        }
        if (!newPassword.equals(newPasswordConfirmation)) {
            return "passwords don't match";
        }
        loggedInUser.setPassword(newPassword);
        return "Successful";
    }

    public void changeSlogan(String newSlogan) {
        loggedInUser.setSlogan(newSlogan);
    }

    public void removeSlogan() {
        loggedInUser.setSlogan("");
    }

    public int showHighScore() {
        return loggedInUser.getHighScore();
    }

    public int showRank() {
       int result = 1;
        for (User user : StrongholdCrusader.getAllUsers().values()) {
            if (user.compareTo(loggedInUser) > 0)result++;
        }
        return result;
    }

    public String showSlogan() {
        return loggedInUser.getSlogan();
    }

    public String showProfile() {
        String result = "";
        result = result.concat("Username: " + loggedInUser.getUsername() + "\n");
        result = result.concat("Nickname: " + loggedInUser.getNickname() + "\n");
        result = result.concat("Rank: " + showRank() + "\n");
        result = result.concat("High Score: " + loggedInUser.getHighScore() + "\n");
        result = result.concat("Slogan: " + loggedInUser.getSlogan() + "\n");
        result = result.concat("Email: " + loggedInUser.getEmail() + "\n");
        return result;
    }
}
