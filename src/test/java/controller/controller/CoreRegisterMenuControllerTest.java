package controller.controller;

import model.StrongholdCrusader;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import view.enums.RegisterMenuRegexes;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CoreRegisterMenuControllerTest {
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    static {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void initializeAndFinalizeUser() {
        CoreRegisterMenuController coreController = new CoreRegisterMenuController(new Scanner(""));
        String output;
        output = coreController.initializeUser("mobin@", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Invalid username format\n", output);
        output = coreController.initializeUser("mobin", "password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Password must contain at least one " +
                "uppercase letter, one lowercase letter and one digit\n", output);
        output = coreController.initializeUser("mobin", "Pass1wAord@1", "mmm.com", "nickname", "slogan");
        assertEquals("Invalid email format\n", output);
        output = coreController.initializeUser("mobin", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertNull(output);
        coreController.finalizeUser("Q", "A");
        output = coreController.initializeUser("mobin", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Username already exists\n", output);
        output = coreController.initializeUser("mobin2", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Email already exists\n", output);
    }

    @ParameterizedTest
    @CsvSource({
            "'user create -u mobin -p Password@1 -n nickname --email mp@mp.com -p pass\nExit'," +
                    " 'Do not determine same field more than once!\n'",
            "'user create -u mobin -p password -n nick --email m@m.com\nExit'," +
                    " 'Password must contain at least one uppercase letter, one lowercase letter and one digit\n'",
            "'user create -u user -p Password@1 -n nick --email m@m.com\n" +
                    "question pick -q 1 -a Tony -c Tony\n" +
                    "Exit'," +
                    "'User registered successfully :)\n'",
    })
    void runTest(String input, String expectedOutput) {
        CoreRegisterMenuController coreController =
                new CoreRegisterMenuController(new Scanner(input));
        coreController.run();
        assertTrue(outContent.toString().endsWith(expectedOutput));
    }

    @Test
    void successfulRegisterTest() {
        String input = "user create -u user -p Password@1 -n nick --email m@m.com\n" +
                "question pick -q 1 -a Tony -c Tony\nExit";
        CoreRegisterMenuController coreController =
                new CoreRegisterMenuController(new Scanner(input));
        coreController.run();
        User user = StrongholdCrusader.getUserByName("user");
        assertNotNull(user);
        assertEquals(0, user.getHighScore());
        assertEquals("user", user.getUsername());
        assertEquals("m@m.com", user.getEmail());
        assertTrue(user.isPasswordCorrect("Password@1"));
        assertEquals("nick", user.getNickname());
        assertEquals("", user.getSlogan());
        assertEquals(StrongholdCrusader.getSecurityQuestions().get(0), user.getSecurityQ());
        assertEquals("Tony", user.getSecurityA());
    }

    @AfterEach
    void tearDown() {
        outContent.reset();
        StrongholdCrusader.reset();
    }
}