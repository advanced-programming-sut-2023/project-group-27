package controller.controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CoreGameMenuControllerTest {
    private User user;
    private Monarchy monarchy;
    private GameMap map;
    private Match match;
    private CoreGameMenuController coreGameController;

    @BeforeEach
    void setUp() {
        User user = new User(
                "test",
                "Password@1",
                "test",
                "test",
                "test",
                "a",
                "b");
        this.user = user;
        this.monarchy = user.getMonarchy();

        User[] users = new User[] {user};
        this.map = new GameMap(100, 100, "map", capacity, keepsLocations);
        this.match = new Match(this.map, Arrays.stream(users).toList());
        this.coreGameController = new CoreGameMenuController(match, new Scanner("Exit"));
    }

    @Test
    void foodListTest() {
        Monarchy monarchy = this.user.getMonarchy();
        monarchy.getGranary().changeGoodsCount(GoodsType.BREAD, 10);
        monarchy.getGranary().changeGoodsCount(GoodsType.CHEESE, 20);
        assertEquals(coreGameController.showFoodList(), "Bread: 10\n" +
                "Apple: 0\n" +
                "Cheese: 20\n" +
                "Meat: 0\n");
    }

    @Test
    void taxRateTest() {
        assertEquals(coreGameController.setTaxRate("9"), "rate should be between -3 and 8\n");
        assertEquals(coreGameController.setTaxRate("-4"), "rate should be between -3 and 8\n");
        assertEquals(coreGameController.setTaxRate("-4@0"), "rate should be an integer\n");
        assertEquals(coreGameController.setTaxRate("3"), "success\n");
        assertEquals(coreGameController.showTaxRate(), "3");
    }

    @Test
    void fearRateTest() {
        assertEquals(coreGameController.setFearRate("6"), "rate should be between -5 and 5\n");
        assertEquals(coreGameController.setFearRate("-6"), "rate should be between -5 and 5\n");
        assertEquals(coreGameController.setFearRate("-4@0"), "rate should be an integer\n");
        assertEquals(coreGameController.setFearRate("3"), "success\n");
        assertEquals(coreGameController.showFearRate(), "3");
    }

    @Test
    void foodRateTest() {
        assertEquals(coreGameController.setFoodRate("3"), "rate should be between -2 and 2\n");
        assertEquals(coreGameController.setFoodRate("-3"), "rate should be between -2 and 2\n");
        assertEquals(coreGameController.setFoodRate("-4@0"), "rate should be an integer\n");
        assertEquals(coreGameController.setFoodRate("1"), "success\n");
        assertEquals(coreGameController.showFoodRate(), "1");
    }

    @Test
    void showMap() {
        assertEquals(coreGameController.showMap("12@1", "12"), "x and y should be integers");
        assertEquals(coreGameController.showMap("110", "5"), "x is out of range it should be between 0 and " +
                (map.getWidth() - 1) + "\n");
        assertEquals(coreGameController.showMap("5", "-5"), "y is out of range it should be between 0 and " +
                (map.getWidth() - 1) + "\n");
        assertNull(coreGameController.showMap("5", "5"));
    }
}