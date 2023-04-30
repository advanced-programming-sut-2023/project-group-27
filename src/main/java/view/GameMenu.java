package view;

import controller.view_controllers.GameMenuController;

import java.util.Scanner;

public class GameMenu {
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller) {
        this.controller = controller;
    }

    public String run(Scanner scanner) {
        System.out.println("Entered Game Menu");
        return null;
    }
}
