package controller;

import controller.controller.CoreRegisterMenuController;

import java.util.Scanner;

public class Controller {
    private final CoreRegisterMenuController coreRegisterMenuController;

    public Controller() {
        coreRegisterMenuController = new CoreRegisterMenuController();
    }

    public void run(Scanner scanner){
        coreRegisterMenuController.run(scanner);
    }

    public void openFile(){
    }

    public void saveFile(){
    }

    public void nextTurn(){

    }
}
