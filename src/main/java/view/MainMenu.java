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
        do {
            String command = scanner.nextLine();
            if (MainMenuRegexes.LOGOUT.getMatcher(command).matches()) {
                controller.logout();
                System.out.println("User " + StrongholdCrusader.getLoggedInUser().getUsername() + " logged out.");
                return "Logout";
            }
            if (MainMenuRegexes.ENTERPROFILEMENU.getMatcher(command).matches()) {
                System.out.println("Entered Profile Menu");
                return "Enter profile menu";
            }
            if (MainMenuRegexes.ENTERMAPEDITMENU.getMatcher(command).matches()) {
                System.out.println("Entered Map Edit Menu");
                return "Enter map edit menu";
            }
            if (MainMenuRegexes.ENTERGAMEMENU.getMatcher(command).matches()) {
                System.out.println("Entered Game Menu");
                return "Enter game menu";
            }

            System.out.println("Invalid command!");
        } while (true);
    }
}
