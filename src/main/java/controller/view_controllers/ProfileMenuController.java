package controller.view_controllers;

import controller.controller.CoreProfileMenuController;
import model.User;
import view.ProfileMenu;

import java.util.Scanner;
import java.util.regex.Matcher;
import controller.controller.Utilities;
import java.util.regex.Pattern;

public class ProfileMenuController {
    private final CoreProfileMenuController coreController;
    private final ProfileMenu menu;
    private User loggedInUser;

    public ProfileMenuController(CoreProfileMenuController coreController, User loggedInUser, Scanner scanner) {
        this.coreController = coreController;
        menu = new ProfileMenu(this, scanner);
        this.loggedInUser = loggedInUser;
    }

    public String changeUsername(Matcher matcher) {
        matcher.matches();
        String newUsername = matcher.group("username");
        if (newUsername.equals(loggedInUser.getUsername())) {
            return "Username can not be the same as old username\n";
        }
        if (newUsername.equals("")) {
            return "empty field\n";
        }
        return coreController.changeUsername(newUsername);
    }

    public String changeNickname(Matcher matcher) {
        matcher.matches();
        String newNickname = matcher.group("nickname");
        if (newNickname.equals("")) {
            return "empty field";
        }
        coreController.changeNickname(newNickname);
        return null;
    }

    public String changePassword(Matcher matcher) {
        matcher.matches();
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        if (loggedInUser.isPasswordCorrect(oldPassword)) {
            return "Current password is incorrect!\n";
        }
        if (oldPassword.equals(newPassword)) {
            return "New password can not be the same as old password";
        }
        if (Utilities.validatePassword(newPassword) != null) {
            return Utilities.validatePassword(newPassword);
        }
        if (!menu.confirm("Please enter your new password again", newPassword)) {
            return "Passwords do not match";
        }
        coreController.changePassword(newPassword);
        return "Successful";
    }

    public String changeSlogan(Matcher matcher) {
        matcher.matches();
        String slogan = matcher.group("slogan");
        if (slogan.equals("")) {
            return "empty field";
        }
        coreController.changeSlogan(slogan);
        return "Successful";
    }

    public String changeEmail(Matcher matcher) {
        matcher.matches();
        String newEmail = matcher.group("email");
        if (newEmail.equals("")) {
            return "empty field";
        }
        if (Utilities.validateEmail(newEmail) != null) {
            return Utilities.validateEmail(newEmail);
        }
        coreController.changeEmail(newEmail);
        return "Successful";
    }

    public void removeSlogan() {
        coreController.removeSlogan();
    }

    public String showHighScore() {
        return String.valueOf(coreController.showHighScore());
    }

    public String showRank() {
        return String.valueOf(coreController.showRank());
    }

    public String showSlogan() {
        return coreController.showSlogan();
    }

    public String showProfile() {
        return coreController.showProfile();
    }

    public ProfileMenu getMenu() {
        return menu;
    }

}
