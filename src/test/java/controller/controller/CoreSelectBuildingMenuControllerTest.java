package controller.controller;

import model.GameMap;
import model.Location;
import model.Match;
import model.User;
import model.building.CivilBuildingType;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CoreSelectBuildingMenuControllerTest {
    private GameMap map;
    private User user, user2;
    private Match match;
    private CoreSelectBuildingMenuController coreController;

    @BeforeEach
    void setUp() {
        map = new GameMap(10, 10, "myMap", 100, null);
        user = new User("user", "Password@1", "n", "s", "e", "q", "a");
        user2 = new User("user2", "Password@1", "n", "s", "e", "q", "a");
        User[] users = new User[]{user, user2};
        match = new Match(map, Arrays.stream(users).toList());
        CoreMapEditMenuController coreMapEditMenuController =
                new CoreMapEditMenuController(match, new Scanner(""));
        coreMapEditMenuController.dropBuilding(new Location(3, 3), CivilBuildingType.CHURCH);
        coreController = new CoreSelectBuildingMenuController(
                map.getCell(3, 3).getBuilding(), new Scanner(""), match.getCurrentMonarchy());
    }

}