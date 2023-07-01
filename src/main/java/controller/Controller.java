package controller;

import com.google.gson.Gson;
import controller.controller.CoreMainMenuController;
import controller.controller.CoreRegisterMenuController;
import controller.controller.Utilities;
import model.GameMap;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.MessengerWrapper;
import model.database.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private final CoreRegisterMenuController coreRegisterMenuController;
    private final CoreMainMenuController coreMainMenuController;

    public Controller(Scanner scanner) {
        coreRegisterMenuController = new CoreRegisterMenuController(scanner);
        this.coreMainMenuController = new CoreMainMenuController(scanner);
    }
    public void run() throws IOException {
        for (User user : StrongholdCrusader.getAllUsers().values()) {
            if (user.isStayLoggedIn()) {
                StrongholdCrusader.setLoggedInUser(user);
                coreMainMenuController.run();
                return;
            }
        }
        coreRegisterMenuController.run();
    }

    public static void fetchData() throws FileNotFoundException {
        File dataFile = new File("src/main/resources/data/data.json");
        Scanner dataScanner = new Scanner(dataFile);
        if (!dataScanner.hasNextLine()) return;

        Gson gson = new Gson();
        String output = dataScanner.nextLine();
        Data fetchedData = gson.fromJson(output, Data.class);
        Data fetchedData1 = gson.fromJson(output, Data.class);
        ArrayList<User> usersToBeAdded = fetchedData.getAllUsers();
        ArrayList<GameMap> gameMapsToBeAdded = fetchedData.getAllMaps();
        ArrayList<GameMap> gameMapsToBeAdded1 = fetchedData.getAllMaps();
        StrongholdCrusader.addAllUsers(usersToBeAdded);
        StrongholdCrusader.addAllMaps(gameMapsToBeAdded);
        StrongholdCrusader.addAllStaticMaps(gameMapsToBeAdded1);
        Messenger.initialize(fetchedData.getWrapper());
        Utilities.addAllDeletedMessages(fetchedData.getDeletedMessages());
    }

    public static void pushData() throws IOException {
        ArrayList<User> users = new ArrayList<>(StrongholdCrusader.getAllUsers().values());
        ArrayList<GameMap> gameMaps = new ArrayList<>(StrongholdCrusader.getAllStaticMaps().values());
        MessengerWrapper wrapper = Messenger.getWrapper();
        ArrayList<Message> deletedMessages = Utilities.getDeletedMessages();

        Data dataToBePushed = new Data(users, gameMaps , wrapper , deletedMessages);

        FileWriter fileWriter = new FileWriter("src/main/resources/data/data.json");
        Gson gson = new Gson();
        String jsonToBePushed = gson.toJson(dataToBePushed);

        fileWriter.write(jsonToBePushed);
        fileWriter.close();
    }
}