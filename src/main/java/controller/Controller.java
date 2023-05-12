package controller;

import com.google.gson.Gson;
import controller.controller.CoreRegisterMenuController;
import model.GameMap;
import model.StrongholdCrusader;
import model.User;
import model.database.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private final CoreRegisterMenuController coreRegisterMenuController;

    public Controller(Scanner scanner) {
        coreRegisterMenuController = new CoreRegisterMenuController(scanner);
    }

    public void run(){
        coreRegisterMenuController.run();
    }

    public void fetchData() throws FileNotFoundException {
        File dataFile = new File("src/main/resources/data/data.json");
        Scanner dataScanner = new Scanner(dataFile);
        if (!dataScanner.hasNextLine()) return;

        Gson gson = new Gson();
        Data fetchedData = gson.fromJson(dataScanner.nextLine(), Data.class);
        Data fetchedData1 = gson.fromJson(dataScanner.nextLine(), Data.class);
        ArrayList<User> usersToBeAdded = fetchedData.getAllUsers();
        ArrayList<GameMap> gameMapsToBeAdded = fetchedData.getAllMaps();
        ArrayList<GameMap> gameMapsToBeAdded1 = fetchedData.getAllMaps();
        StrongholdCrusader.addAllUsers(usersToBeAdded);
        StrongholdCrusader.addAllMaps(gameMapsToBeAdded);
        StrongholdCrusader.addAllStaticMaps(gameMapsToBeAdded1);
    }

    public void pushData() throws IOException {
        ArrayList<User> users = new ArrayList<>(StrongholdCrusader.getAllUsers().values());
        ArrayList<GameMap> gameMaps = new ArrayList<>(StrongholdCrusader.getAllStaticMaps().values());
        Data dataToBePushed = new Data(users, gameMaps);

        FileWriter fileWriter = new FileWriter("src/main/resources/data/data.json");
        Gson gson = new Gson();
        String jsonToBePushed = gson.toJson(dataToBePushed);

        fileWriter.write(jsonToBePushed);
        fileWriter.close();
    }

}