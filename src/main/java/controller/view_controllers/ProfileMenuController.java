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
        if (!loggedInUser.isPasswordCorrect(oldPassword)) {
            return "Current password is incorrect!\n";
        }
        if (coreController.changePasswordPrep(oldPassword, newPassword) != null) {
            return coreController.changePasswordPrep(oldPassword, newPassword);
        }
        coreController.changePassword(newPassword);
        return "Successful\n";
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
        return coreController.changeEmail(newEmail);
    }

    public void removeSlogan() {
        coreController.removeSlogan();
    }

    public String showHighScore() {
        return String.valueOf(coreController.showHighScore()) + "\n";
    }

    public String showRank() {
        return String.valueOf(coreController.showRank()) + "\n";
    }

    public String showSlogan() {
        return coreController.showSlogan() + "\n";
    }

    public String showProfile() {
        return coreController.showProfile();
    }

    public ProfileMenu getMenu() {
        return menu;
    }

}
