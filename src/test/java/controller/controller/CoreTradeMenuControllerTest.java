package controller.controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CoreTradeMenuControllerTest {
    private ByteArrayOutputStream outContent;
    private CoreTradeMenuController coreTradeMenuController;
    private User user, user2;
    private Match match;

    @BeforeEach
    void setUp() {
        this.user = new User("user", "Password@1", "n", "s", "e", "a", "b");
        this.user2 = new User("user2", "Password@1", "n", "s", "e", "a", "b");
        user.setMonarchy(new Monarchy(user, MonarchyColorType.BLACK));
        user2.setMonarchy(new Monarchy(user2, MonarchyColorType.RED));
        User[] users = new User[]{user, user2};
        this.match = new Match(new GameMap(10, 10, "myMap", 100, null), Arrays.stream(users).toList());
        this.outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void createTrade() {
        this.coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                "trade -t Iron -a 10 -p 100 -m \"hello\" -u user2\nExit"));
        this.coreTradeMenuController.run();
        assertTrue(outContent.toString().contains("trade request sent"));
    }

    @Test
    void createTradeNoneExistingGood() {
        this.coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                "trade -t NoneExistingGood -a 10 -p 100 -m \"hello\" -u user2\nExit"));
        this.coreTradeMenuController.run();
        assertTrue(outContent.toString().contains("no such goods type"));
    }

    @Test
    void createTradeWithYourself() {
        this.coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                "trade -t Iron -a 10 -p 100 -m \"hello\" -u user\nExit"));
        this.coreTradeMenuController.run();
        assertTrue(outContent.toString().contains("you can't trade with yourself"));
    }

    @Test
    void createTradeNoneInteger() {
        this.coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                "trade -t Iron -a 10 -p 1@00 -m \"hello\" -u user2\nExit"));
        this.coreTradeMenuController.run();
        assertTrue(outContent.toString().contains("goods amount and price must be numeric"));
    }

    @Test
    void createTradeNegativePrice() {
        this.coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                "trade -t Iron -a 10 -p -100 -m \"hello\" -u user2\nExit"));
        this.coreTradeMenuController.run();
        assertTrue(outContent.toString().contains("price must be non-negative"));
    }

    @Test
    void createTradeNegativeAmount() {
        this.coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                "trade -t Iron -a 0 -p 100 -m \"hello\" -u user2\nExit"));
        this.coreTradeMenuController.run();
        assertTrue(outContent.toString().contains("goods amount must be positive"));
    }

    @Nested
    class TradeCreated {
        @BeforeEach
        void setUp() {
            coreTradeMenuController = new CoreTradeMenuController(match, user, new Scanner(
                    "trade -t Iron -a 10 -p 100 -m \"hello\" -u user2\nExit"));
            coreTradeMenuController.run();
        }

        @Test
        void acceptTradeNoneNumericId() {
            coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                    "trade accept -i asd -m \"ABCDEFU\"\nExit"));
            coreTradeMenuController.run();
            assertTrue(outContent.toString().contains("id must be numeric"));
        }

        @Test
        void acceptTradeInvalidId() {
            coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                    "trade accept -i 2 -m \"ABCDEFU\"\nExit"));
            coreTradeMenuController.run();
            assertTrue(outContent.toString().contains("no such trade"));
        }

        @Test
        void acceptTradeNotEnoughGood() {
            coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                    "trade accept -i 1001 -m \"ABCDEFU\"\nExit"));
            coreTradeMenuController.run();
            assertTrue(outContent.toString().contains("Id: 1001\n" +
                    "Owner: user\n" +
                    "Type: Iron\n" +
                    "Amount: 10\n" +
                    "Price: 100\n" +
                    "Comment: \"hello\"\n" +
                    "State: awaiting"));
            assertTrue(outContent.toString().contains("not enough goods to accept this trade"));
        }

        @Test
        void acceptTradeNotEnoughGold() {
            user2.getMonarchy().getStockPile().changeGoodsCount(GoodsType.IRON, 10);
            coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                    "trade accept -i 1001 -m \"ABCDEFU\"\nExit"));
            coreTradeMenuController.run();
            assertTrue(outContent.toString().contains(
                    "trade owner does not have enough gold to trade with you"));
        }

        @Test
        void acceptTradeSuccessful() {
            user2.getMonarchy().getStockPile().changeGoodsCount(GoodsType.IRON, 10);
            user.getMonarchy().changeGold(100);
            coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                    "trade accept -i 1001 -m \"ABCDEFU\"\nExit"));
            coreTradeMenuController.run();
            assertTrue(outContent.toString().contains("trade accepted"));
            assertEquals(0, user.getMonarchy().getGold());
            assertEquals(100, user2.getMonarchy().getGold());
            assertEquals(0, user2.getMonarchy().getStockPile().getGoodsCount(GoodsType.IRON));
            assertEquals(10, user.getMonarchy().getStockPile().getGoodsCount(GoodsType.IRON));
        }

        @Test
        void showTradeList() {
            coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                    "trade list\nExit"));
            coreTradeMenuController.run();
            assertTrue(outContent.toString().contains("Id: 1001\n" +
                    "Owner: user\n" +
                    "Type: Iron\n" +
                    "Amount: 10\n" +
                    "Price: 100\n" +
                    "Comment: \"hello\"\n" +
                    "State: awaiting"));
        }

        @Nested
        class TradeAccepted {
            @BeforeEach
            void setUp() {
                user2.getMonarchy().getStockPile().changeGoodsCount(GoodsType.IRON, 10);
                user.getMonarchy().changeGold(100);
                coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                        "trade accept -i 1001 -m \"ABCDEFU\"\nExit"));
                coreTradeMenuController.run();
            }

            @Test
            void showHistory() {
                coreTradeMenuController = new CoreTradeMenuController(match, user2, new Scanner(
                        "trade history\nExit"));
                coreTradeMenuController.run();
                assertTrue(outContent.toString().contains("Id: 1001\n" +
                        "Owner: user\n" +
                        "Type: Iron\n" +
                        "Amount: 10\n" +
                        "Price: 100\n" +
                        "Comment: \"hello\"\n" +
                        "State: done"));
            }
        }
    }
}