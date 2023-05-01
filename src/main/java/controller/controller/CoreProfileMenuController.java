package controller.controller;

import controller.view_controllers.ProfileMenuController;
import model.StrongholdCrusader;
import model.User;
import view.ProfileMenu;

import java.util.Scanner;

public class CoreProfileMenuController {
    private final Scanner scanner;
    private final User loggedInUser;
    private final ProfileMenu profileMenu;
    private final ProfileMenuController controller;

    public CoreProfileMenuController(Scanner scanner){
        this.scanner = scanner;
        this.loggedInUser = StrongholdCrusader.getLoggedInUser();
        this.controller = new ProfileMenuController(this, StrongholdCrusader.getLoggedInUser(), scanner);
        this.profileMenu = this.controller.getMenu();
    }
    public void run(){
        profileMenu.run();
    }

    public String changeUsername(String username) {
        if (username.equals(loggedInUser.getUsername())) {
            return "Username can not be the same as old username\n";
        }
        if (username.equals("")) {
            return "empty field\n";
        }
        if (!username.matches("[0-9a-zA-Z_]+")) {
            return "Invalid username format\n";
        }
        StrongholdCrusader.getAllUsers().remove(loggedInUser.getUsername());
        loggedInUser.setUsername(username);
        StrongholdCrusader.getAllUsers().put(username, loggedInUser);
        return "Successful\n";
    }

    public void changeNickname(String nickname) {
        loggedInUser.setNickname(nickname);
    }

    public boolean isPasswordValid(String oldPassword) {
        return loggedInUser.isPasswordCorrect(oldPassword);
    }

    public String changePasswordPrep(String oldPassword, String newPassword) {
        if (!isPasswordValid(oldPassword)) {
            return "Current password is incorrect!\n";
        }
        if (oldPassword.equals(newPassword)) {
            return "New password can not be the same as old password\n";
        }
        if (Utilities.validatePassword(newPassword) != null) {
            return Utilities.validatePassword(newPassword);
        }
        return null;
    }

    public void changePassword(String newPassword) {
        loggedInUser.setPassword(newPassword);
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
            if (user.compareTo(loggedInUser) < 0)result++;
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

    public String changeEmail(String newEmail) {
        if (newEmail.equals("")) {
            return "empty field\n";
        }
        if (Utilities.validateEmail(newEmail) != null) {
            return Utilities.validateEmail(newEmail);
        }
        if (StrongholdCrusader.getAllUsers().values().stream()
                .anyMatch(user -> user.getEmail().equals(newEmail))) {
            return "Email already exists\n";
        }
        loggedInUser.setEmail(newEmail);
        return "Successful\n";
    }
}
