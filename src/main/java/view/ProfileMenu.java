package view;

import controller.view_controllers.ProfileMenuController;
import view.enums.ProfileMenuRegexes;

import java.util.Scanner;

public class ProfileMenu extends MenuBase{
    private final ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        String command , output;
        while (true) {
            command = scanner.nextLine();
            if (ProfileMenuRegexes.CHANGE_USERNAME.getMatcher(command).matches()) {
                output = controller.changeUsername(
                        ProfileMenuRegexes.CHANGE_USERNAME.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.CHANGE_NICKNAME.getMatcher(command).matches()) {
                output = controller.changeNickname(
                        ProfileMenuRegexes.CHANGE_NICKNAME.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.CHANGE_PASSWORD.getMatcher(command).matches()) {
                output = controller.changePassword(
                        ProfileMenuRegexes.CHANGE_PASSWORD.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.CHANGE_SLOGAN.getMatcher(command).matches()) {
                output = controller.changeSlogan(
                        ProfileMenuRegexes.CHANGE_SLOGAN.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.CHANGE_EMAIL.getMatcher(command).matches()) {
                output = controller.changeEmail(
                        ProfileMenuRegexes.CHANGE_EMAIL.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.REMOVE_SLOGAN.getMatcher(command).matches()) {
                controller.removeSlogan();
                continue;
            }
            if (ProfileMenuRegexes.DISPLAY_HIGH_SCORE.getMatcher(command).matches()) {
                output = controller.showHighScore();
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.DISPLAY_RANK.getMatcher(command).matches()) {
                output = controller.showRank();
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.DISPLAY_SLOGAN.getMatcher(command).matches()) {
                output = controller.showSlogan();
                System.out.print(output);
                continue;
            }
            if (ProfileMenuRegexes.DISPLAY_PROFILE.getMatcher(command).matches()) {
                output = controller.showProfile();
                System.out.print(output);
                continue;
            }
            if (command.equals("Exit")) {
                break;
            }
        }
        return "Exit";
    }
}
