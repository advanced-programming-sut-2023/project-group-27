package controller.controller;

import controller.view_controllers.MainMenuController;
import model.StrongholdCrusader;
import view.MainMenu;

import java.util.Scanner;

public class CoreMainMenuController {
    private final MainMenu mainMenu;
    private final CoreMapEditMenuController coreMapEditMenuController;
    private final CoreProfileMenuController coreProfileMenuController;
    private final CoreGameMenuController coreGameMenuController;

    public CoreMainMenuController(){
        mainMenu = new MainMenu(new MainMenuController());
        coreProfileMenuController = new CoreProfileMenuController();
        coreMapEditMenuController = new CoreMapEditMenuController();
        coreGameMenuController = new CoreGameMenuController(StrongholdCrusader.getCurrentUser());
    }
    public String run(Scanner scanner){
        String mainMenuResult;
        while (true) {
            mainMenuResult = mainMenu.run(scanner);
            switch (mainMenuResult) {
                case "Logout":
                    return "Logout";
                case "Enter profile menu":
                    coreProfileMenuController.run(scanner);
                case "Enter map edit menu":
                    coreMapEditMenuController.run(scanner);
                case "Enter game menu":
                    coreGameMenuController.run(scanner);
            }
        }
    }
}
