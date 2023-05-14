package controller.controller;

import model.*;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CoreGameMenuControllerTest {
    private User user, user2;
    private Monarchy monarchy;
    private GameMap map;
    private Match match;
    private CoreGameMenuController coreGameController;

    @BeforeEach
    void setUp() {
        Utilities.init();
        User user = new User(
                "test",
                "Password@1",
                "test",
                "test",
                "test",
                "a",
                "b");
        User user2 = new User(
                "test2",
                "Password@1",
                "test2",
                "test2",
                "test2",
                "a",
                "b");
        this.user = user;
        this.user2 = user2;

        User[] users = new User[] {user, user2};
        Location locations[] = new Location[] {new Location(1, 1), new Location(10, 10)};
        this.map = new GameMap(100, 100, "map", 100, locations);
        user.setMonarchy(new Monarchy(user, MonarchyColorType.BLACK, map, locations[0]));
        user2.setMonarchy(new Monarchy(user2, MonarchyColorType.RED, map, locations[1]));
        this.monarchy = user.getMonarchy();
        this.match = new Match(this.map, Arrays.stream(users).toList());
        this.coreGameController = new CoreGameMenuController(match, new Scanner(""));
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

    @Nested
    class MapNavigationTest {
        private ByteArrayOutputStream outContent;
        @BeforeEach
        void setUp() {
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
        }

        @Test
        void showMap() {
            coreGameController = new CoreGameMenuController(match, new Scanner("Exit"));
            assertEquals(coreGameController.showMap("12@1", "12"), "x and y should be integers");
            assertEquals(coreGameController.showMap("110", "5"), "x is out of range it should be between 0 and " +
                    (map.getWidth() - 1) + "\n");
            assertEquals(coreGameController.showMap("5", "-5"), "y is out of range it should be between 0 and " +
                    (map.getWidth() - 1) + "\n");
            assertNull(coreGameController.showMap("5", "5"));
        }
    }

    @Nested
    class SelectUnitTest {
        private ByteArrayOutputStream outContent;
        @BeforeEach
        void setUp() {
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            CoreMapEditMenuController coreMapEditMenuController = new CoreMapEditMenuController(match, new Scanner(""));
            coreMapEditMenuController.dropUnit(new Location(5, 5), SoldierType.ARCHER, 10);
            coreMapEditMenuController.setTexture(new Location(5, 6), LandType.GRASS);
            Location[] locations = new Location[] {new Location(4, 4), new Location(5, 4),
                    new Location(6, 4), new Location(4, 5), new Location(6, 5),
                    new Location(6, 6), new Location(4, 6)};
            for (Location location : locations) {
                coreMapEditMenuController.setTexture(location, LandType.ROCKY);
            }
        }

        @Test
        void selectUnit() {
            coreGameController = new CoreGameMenuController(match, new Scanner("move unit to -x 5 -y 6\nExit"));
            coreGameController.selectUnit("5", "5", "Archer");
            for (Monarchy matchMonarchy : match.getMonarchies()) {
                matchMonarchy.setLord(new Soldier(SoldierType.LORD, matchMonarchy.getKing()));
            }
            match.nextTurn();match.nextTurn();
            List<Man> men = map.getCell(5, 6).getMen();
            assertEquals(men.size(), 10);
            assertEquals("Archer", men.get(0).getName());
            assertEquals("test", men.get(0).getOwner().getNickname());
        }

        @Test
        void selectUnit2() {
            coreGameController = new CoreGameMenuController(match, new Scanner(
                    "patrol unit --x1 5 --y1 6 --x2 5 --y2 9\nExit"));
            coreGameController.selectUnit("5", "5", "Archer");
            for (Monarchy matchMonarchy : match.getMonarchies()) {
                matchMonarchy.setLord(new Soldier(SoldierType.LORD, matchMonarchy.getKing()));
            }
            assertEquals(0, monarchy.getFoodRate());
            match.nextTurn();match.nextTurn();
            assertEquals(-2, monarchy.getFoodRate());
            List<Man> men = map.getCell(5, 6).getMen();
            assertEquals(men.size(), 10);
            assertEquals("Archer", men.get(0).getName());
            assertEquals("test", men.get(0).getOwner().getNickname());
            for (int i = 0; i < 4; i++) {
                match.nextTurn();
            }
            men = map.getCell(5, 9).getMen();
            assertEquals(men.size(), 10);
            assertEquals("Archer", men.get(0).getName());
            assertEquals("test", men.get(0).getOwner().getNickname());
            for (int i = 0; i < 4; i++) {
                match.nextTurn();
            }
            men = map.getCell(5, 6).getMen();
            assertEquals(men.size(), 10);
            assertEquals("Archer", men.get(0).getName());
            assertEquals("test", men.get(0).getOwner().getNickname());
        }

        @Test
        void selectUnit3() {
            List<Man> men = map.getCell(5, 5).getMen();
            assertTrue(men.stream().allMatch(man -> ((Soldier) man).getState().equals("standing")));
            coreGameController = new CoreGameMenuController(match, new Scanner(
                    "set -x 5 -y 5 -s offensive\nExit"));
            coreGameController.selectUnit("5", "5", "Archer");
            assertFalse(men.stream().anyMatch(man -> !(man instanceof Soldier)));
            assertTrue(men.stream().allMatch(man -> ((Soldier) man).getState().equals("offensive")));
        }
    }

    @Nested
    class ShopTest {
        private ByteArrayOutputStream outContent;
        @BeforeEach
        void setUp() {
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            match.getCurrentMonarchy().changeGold(GoodsType.IRON.getShopBuyPrice() * 2);
        }

        @Test
        void showShop() {
            match.getCurrentMonarchy().getStockPile().changeGoodsCount(GoodsType.IRON, 100);
            coreGameController = new CoreGameMenuController(match, new Scanner("show price list\nExit"));
            coreGameController.enterShop();
            assertTrue(outContent.toString().contains(
                    "Iron => sell price: " + GoodsType.IRON.getShopSellPrice() +
                            " | buy price: " + GoodsType.IRON.getShopBuyPrice() +
                            " | amount: 100"));
        }

        @Test
        void shopNotEnoughMoney() {
            coreGameController = new CoreGameMenuController(match, new Scanner("buy -i Iron -a 3\nExit"));
            coreGameController.enterShop();
            assertTrue(outContent.toString().contains("You do not have enough gold!"));
        }

        @Test
        void shopInvalidNumber() {
            coreGameController = new CoreGameMenuController(match, new Scanner("buy -i Iron -a -2\nExit"));
            coreGameController.enterShop();
            assertTrue(outContent.toString().contains("Invalid amount number!"));
        }

        @Test
        void shopSuccessfulPurchase() {
            coreGameController = new CoreGameMenuController(match, new Scanner("buy -i Iron -a 2\nExit"));
            coreGameController.enterShop();
            assertTrue(outContent.toString().trim().endsWith("Buy successful!"));
            assertEquals(match.getCurrentMonarchy().getStorage().getOrDefault(GoodsType.IRON, 0), 2);
        }


        @Nested
        class Sell {
            @BeforeEach
            void setUp() {
                monarchy.getStockPile().changeGoodsCount(GoodsType.IRON, 2);
            }

            @Test
            void checkInit() {
                assertEquals(2, monarchy.getStockPile().getMap().getOrDefault(GoodsType.IRON, 0));
            }

            @Test
            void shopSellNotEnoughGood() {
                coreGameController = new CoreGameMenuController(match, new Scanner("sell -i Iron -a 3\nExit"));
                coreGameController.enterShop();
                assertTrue(outContent.toString().contains("You do not have enough item to sell!"));
            }

            @Test
            void InvalidAmount() {
                coreGameController = new CoreGameMenuController(match, new Scanner("sell -i Iron -a -2\nExit"));
                coreGameController.enterShop();
                assertTrue(outContent.toString().contains("Invalid amount number!"));
            }

            @Test
            void Successful() {
                coreGameController = new CoreGameMenuController(
                        match, new Scanner("sell -i Iron -a 2\nExit"));
                coreGameController.enterShop();
                assertTrue(outContent.toString().trim().endsWith("Sell successful!"));
                GoodsType iron = GoodsType.IRON;
                assertEquals(0, monarchy.getStockPile().getMap().getOrDefault(iron, 0));
                assertEquals(2 * (iron.getShopBuyPrice() + iron.getShopSellPrice()),
                        monarchy.getGold());
            }
        }
    }
}