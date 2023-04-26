package controller.controller;

import controller.view_controllers.MainMenuController;
import model.StrongholdCrusader;
import view.MainMenu;

import java.util.Scanner;

public class CoreMainMenuController {
    private final Scanner scanner;
    private final MainMenu mainMenu;
    private final CoreMapEditMenuController coreMapEditMenuController;
    private final CoreProfileMenuController coreProfileMenuController;
    private final CoreGameMenuController coreGameMenuController;

    public CoreMainMenuController(Scanner scanner){
        this.scanner = scanner;
        mainMenu = new MainMenu(new MainMenuController(this));
        coreProfileMenuController = new CoreProfileMenuController(scanner);
        coreMapEditMenuController = new CoreMapEditMenuController(scanner);
        coreGameMenuController = new CoreGameMenuController(StrongholdCrusader.getCurrentUser(), scanner);
    }
    public String run(){
        String mainMenuResult;
        while (true) {
            mainMenuResult = mainMenu.run(scanner);
            switch (mainMenuResult) {
                case "Logout":
                    return "Logout";
                case "Enter profile menu":
                    coreProfileMenuController.run();
                    break;
                case "Enter map edit menu":
                    coreMapEditMenuController.run();
                    break;
                case "Enter game menu":
                    coreGameMenuController.run();
                    break;
            }
        }
    }

    public void logout() {
        StrongholdCrusader.getCurrentUser().setStayLoggedIn(false);
    }
}
