package view;

import controller.view_controllers.MainMenuController;
import model.StrongholdCrusader;
import view.enums.MainMenuRegexes;

import java.util.Scanner;

public class MainMenu {
    private final MainMenuController controller;

    public MainMenu(MainMenuController controller) {
        this.controller = controller;
    }

    public String run(Scanner scanner){
        System.out.println("Entered Main Menu");
        System.out.println("your options are:" +
                "\n1. Enter profile menu" +
                "\n2. Enter map edit menu" +
                "\n3. Enter game menu" +
                "\n4. Logout");

        do {
            String command = scanner.nextLine();
            if (MainMenuRegexes.LOGOUT.getMatcher(command).matches()) {
                controller.logout();
                System.out.println("User " + StrongholdCrusader.getLoggedInUser().getUsername() + " logged out.");
                return "Logout";
            }
            if (MainMenuRegexes.ENTER_PROFILE_MENU.getMatcher(command).matches()) {
                return "Enter profile menu";
            }
            if (MainMenuRegexes.ENTER_MAP_EDIT_MENU.getMatcher(command).matches()) {
                return "Enter map edit menu";
            }
            if (MainMenuRegexes.ENTER_GAME_MENU.getMatcher(command).matches()) {
                return "Enter game menu";
            }

            System.out.println("Invalid command!");
        } while (true);
    }
}
