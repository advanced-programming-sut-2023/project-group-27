package controller.controller;

import model.*;
import model.man.SoldierType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CoreMapEditMenuControllerTest {
    private GameMap map;
    private User user;
    private Match match;
    private CoreMapEditMenuController coreMapEditMenuController;

    @BeforeEach
    void setUp() {
        Location[] locations = new Location[] {new Location(1, 1), new Location(9, 9)};
        this.map = new GameMap(10, 10, "myMap", 100, locations);
        this.user = new User("user", "Password@1", "user",
                "user", "user@u.com", "a", "b");
        user.setMonarchy(new Monarchy(user, MonarchyColorType.BLACK, map, locations[0]));
        User[] users = new User[]{user};
        this.match = new Match(map, Arrays.stream(users).toList());
        this.coreMapEditMenuController =
                new CoreMapEditMenuController(match, null);

    }

    @Test
    void setTexture() {
        assertEquals("Location out of bounds!",
                coreMapEditMenuController.setTexture(new Location(-1, 0),
                        LandType.ROCKY));
        assertEquals("Location 1 is out of bounds!",
                coreMapEditMenuController.setTexture(new Location(0, -1),
                        new Location(2, 3), LandType.ROCKY));
        assertEquals("Location 2 is out of bounds!",
                coreMapEditMenuController.setTexture(new Location(0, 0),
                        new Location(10, 3), LandType.ROCKY));
        assertEquals("Invalid Locations for Rectangle points!",
                coreMapEditMenuController.setTexture(new Location(5, 0),
                        new Location(4, 3), LandType.ROCKY));
        coreMapEditMenuController.dropUnit(new Location(2, 2), SoldierType.SWORDSMAN, 3);
        assertEquals("A building, tree or person is in this location. can't edit texture.",
                coreMapEditMenuController.setTexture(new Location(2, 2), LandType.ROCKY));
        assertEquals("Texture changed successfully!", coreMapEditMenuController
                .setTexture(new Location(1, 1), LandType.ROCKY));
        assertEquals("There's  building, tree or person in x: 2 and y: 1. can't change the texture!\n" +
                        "There's  building, tree or person in x: 2 and y: 2. can't change the texture!\n" +
                        "Texture of locations not specified above have successfully changed!",
                coreMapEditMenuController.setTexture(new Location(1, 1),
                        new Location(3, 3), LandType.ROCKY));
        assertEquals(map.getCell(1, 1).getType(), LandType.ROCKY);
    }

    @Nested
    class TextureHasBeenSet {
        @BeforeEach
        void setUp() {
            coreMapEditMenuController.dropUnit(new Location(2, 2), SoldierType.SWORDSMAN, 3);
            coreMapEditMenuController.setTexture(new Location(1, 1), new Location(3, 3), LandType.ROCKY);
        }

        @Test
        void clear() {
            coreMapEditMenuController.clear(new Location(1, 1));
            assertEquals(LandType.PLAIN, map.getCell(1, 1).getType());
            coreMapEditMenuController.clear(new Location(2, 2));
            assertEquals(0, map.getCell(2, 2).getMen().size());
        }
    }
}