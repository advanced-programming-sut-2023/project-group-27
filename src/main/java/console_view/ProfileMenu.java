package console_view;

import controller.view_controllers.ProfileMenuController;
import console_view.enums.ProfileMenuRegexes;

import java.util.Scanner;

public class ProfileMenu extends MenuBase{
    private final ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        System.out.print("Entered Profile Menu.\n");
        System.out.print("your options are:\n" +
                "1. profile change -u <new username>\n" +
                "2. profile change -n <new nickname>\n" +
                "3. profile change -o <current password> -n <new password>\n" +
                "4. profile change -e [new email]\n" +
                "5. profile change slogan -s <new slogan>\n" +
                "6. profile remove slogan\n" +
                "7. profile display highscore\n" +
                "8. profile display rank\n" +
                "9. profile display slogan\n" +
                "10. profile display\n" +
                "11. Exit\n");
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
            System.out.print("Invalid command\n");
        }
        return "Exit";
    }
}
