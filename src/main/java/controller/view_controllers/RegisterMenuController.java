package controller.view_controllers;

import controller.controller.CoreRegisterMenuController;
import view.RegisterMenu;

import java.util.regex.Matcher;

public class RegisterMenuController {
    private CoreRegisterMenuController coreController;
    private RegisterMenu menu;

    public RegisterMenuController(CoreRegisterMenuController coreController) {
        this.coreController = coreController;
        menu = new RegisterMenu(this);
    }

    public String createUser(Matcher matcher) {
        return "User created successfully";
    }
}
