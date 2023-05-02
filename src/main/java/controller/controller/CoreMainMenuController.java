package controller.controller;

import controller.view_controllers.MainMenuController;
import controller.view_controllers.ProfileMenuController;
import model.StrongholdCrusader;
import view.MainMenu;

import java.util.Scanner;

public class CoreMainMenuController {
    private final Scanner scanner;
    private final MainMenu mainMenu;

    public CoreMainMenuController(Scanner scanner){
        this.scanner = scanner;
        MainMenuController mainController = new MainMenuController(this);
        mainMenu = new MainMenu(mainController);
    }
    public String run(){
        String mainMenuResult;
        while (true) {
            mainMenuResult = mainMenu.run(scanner);
            switch (mainMenuResult) {
                case "Logout":
                    return "Logout";
                case "Enter profile menu":
                    CoreProfileMenuController coreProfileController =
                            new CoreProfileMenuController(scanner);
                    coreProfileController.run();
                    break;
                case "Enter map edit menu":
                    CoreMapEditMenuController coreMapEditController = new CoreMapEditMenuController(scanner);
                    coreMapEditController.run();
                    break;
                case "Enter game menu":
                    CoreGameMenuController coreGameController =
                            new CoreGameMenuController(null, scanner);
                    coreGameController.run();
                    break;
            }
        }
    }

    public void logout() {
        StrongholdCrusader.getLoggedInUser().setStayLoggedIn(false);
    }
}