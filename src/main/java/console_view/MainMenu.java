package console_view;

import controller.view_controllers.MainMenuController;
import model.StrongholdCrusader;
import console_view.enums.MainMenuRegexes;

import java.util.Scanner;

public class MainMenu {
    private final MainMenuController controller;
    private final Scanner scanner;

    public MainMenu(MainMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run(){
        System.out.println("Entered console_view.Main Menu");
        System.out.println("your options are:" +
                "\n1. Enter profile menu" +
                "\n2. Enter game start menu" +
                "\n3. Logout" +
                "\n4. Exit");

        do {
            String command = scanner.nextLine();
            if (MainMenuRegexes.LOGOUT.getMatcher(command).matches()) {
                controller.logout();
                System.out.println("User " + StrongholdCrusader.getLoggedInUser().getUsername() + " logged out.");
                return "Logout";
            }
            if (MainMenuRegexes.EXIT.getMatcher(command).matches()) {
                System.out.println("User " + StrongholdCrusader.getLoggedInUser().getUsername() + " Exited main menu.");
                return "Exit";
            }
            if (MainMenuRegexes.ENTER_PROFILE_MENU.getMatcher(command).matches()) {
                return "Enter profile menu";
            }
            if (MainMenuRegexes.ENTER_GAME_START_MENU.getMatcher(command).matches()) {
                return "Enter game start menu";
            }

            System.out.println("Invalid command!");
        } while (true);
    }
}