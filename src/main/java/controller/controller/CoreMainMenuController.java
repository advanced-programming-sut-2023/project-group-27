package controller.controller;

import controller.view_controllers.MainMenuController;
import model.StrongholdCrusader;
import view.MainMenu;

import java.io.IOException;
import java.util.Scanner;

public class CoreMainMenuController {
    private final Scanner scanner;
    private final MainMenu mainMenu;
    public CoreMainMenuController(Scanner scanner){
        this.scanner = scanner;
        MainMenuController mainController = new MainMenuController(this);
        mainMenu = new MainMenu(mainController, scanner);
    }
    public String run() throws IOException {
        String mainMenuResult;
        while (true) {
            mainMenuResult = mainMenu.run();
            switch (mainMenuResult) {
                case "Logout":
                    return "Logout";
                case "Enter profile menu":
                    CoreProfileMenuController coreProfileController =
                            new CoreProfileMenuController(scanner);
                    coreProfileController.run();
                    break;
                case "Enter map edit menu":
                    CoreMapEditMenuController coreMapEditController = new CoreMapEditMenuController(null, scanner);
                    coreMapEditController.run();
                    break;
                case "Enter game start menu":
                    CoreGameStartMenuController coreGameStartController =
                            new CoreGameStartMenuController(scanner, StrongholdCrusader.getLoggedInUser());
                    coreGameStartController.run();
                    break;
            }
        }
    }

    public void logout() {
        StrongholdCrusader.getLoggedInUser().setStayLoggedIn(false);
    }
}