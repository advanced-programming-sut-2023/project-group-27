package controller.view_controllers;

import controller.controller.CoreLoginMenuController;
import controller.controller.CoreRegisterMenuController;
import model.StrongholdCrusader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import view.enums.LoginMenuRegexes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void setUp() {
        CoreRegisterMenuController controller = new CoreRegisterMenuController(new Scanner(""));
        controller.initializeUser("mazdak" , "Password@1" , "maz@maz.com" , "mazamaz" , null);
        controller.finalizeUser("aaaaaa" , "bbbbb");
    }

    @DisplayName("Login")
    @ParameterizedTest
    @CsvSource({
            "user login -u rick -p <password> , This username does not exist!",
            "user login -u mazdak -p Password , Username and password did not match!",
            "user login -u mazdak -p Password@1 , User logged in successfully!",
    })
    void login(String command , String expectedError) {
        Matcher matcher = LoginMenuRegexes.LOGIN.getMatcher(command);
        LoginMenuController controller =
                new CoreLoginMenuController(new Scanner("")).getLoginController();
        String output = controller.login(matcher);
        assertEquals(expectedError , output);
    }

    @Disabled
    @DisplayName("Delay test")
    @Test
    void delayTest() throws InterruptedException {
        CoreLoginMenuController coreController = new CoreLoginMenuController(new Scanner(System.in));
        String output = coreController.login("mazdak", "pass" , false);
        assertEquals("Username and password did not match!" , output);
        output = coreController.login("mazdak", "Password@1" , false);
        assertEquals("You need to wait another 5 seconds to login!" , output);
        Thread.sleep(5000);
        output = coreController.login("mazdak", "Password" , false);
        assertEquals("Username and password did not match!" , output);
        Thread.sleep(5000);
        output = coreController.login("mazdak", "Password@1" , false);
        assertEquals("You need to wait another 5 seconds to login!" , output);
        Thread.sleep(5000);
        output = coreController.login("mazdak", "Password@1" , false);
        assertEquals("User logged in successfully!" , output);
    }

    @DisplayName("ّForget Password")
    @ParameterizedTest
    @CsvSource({
            "'forgot my password\nmazdak\nbbbbb\nPassword@1\nPassword@1\nExit'," +
                    "'Entered Login Menu\r\nyour options are:\n" +
                    "1. user login -u <username> -p <password> --stayLoggedIn (optional)\n" +
                    "2. forgot my password -u <username>\n" +
                    "3. Exit\n"+
                    "Please enter your username\r\n" +
                    "Please answer the security question\r\n" +
                    "aaaaaa\r\nEnter a new password\r\nPlease confirm your new password\r\n" +
                    "Password changed successfully!\r\n'",
            "'forgot my password\nmani\nExit'," +
                    "'Entered Login Menu\r\n" +
                    "your options are:\n" +
                    "1. user login -u <username> -p <password> --stayLoggedIn (optional)\n" +
                    "2. forgot my password -u <username>\n" +
                    "3. Exit\n" +
                    "Please enter your username\r\nThis username does not exist!\r\n'",
            "'forgot my password\nmazdak\nbbb\nbbbbb\nPassword@2\nPassword@1\nPassword@2\nExit'," +
                    "'Entered Login Menu\r\n" +
                    "your options are:\n" +
                    "1. user login -u <username> -p <password> --stayLoggedIn (optional)\n" +
                    "2. forgot my password -u <username>\n" +
                    "3. Exit\n" +
                    "Please enter your username\r\n" +
                    "Please answer the security question\r\n" +
                    "aaaaaa\r\n" +
                    "Wrong answer!\r\n" +
                    "aaaaaa\r\n" +
                    "Enter a new password\r\n" +
                    "Please confirm your new password\r\n" +
                    "The confirmation does not match the password\r\n" +
                    "Please confirm your new password\r\n" +
                    "Password changed successfully!\r\n'"
    })
    void forgetPassword(String command , String expectedError) throws IOException {
        CoreLoginMenuController coreController = new CoreLoginMenuController(new Scanner(command));
        coreController.run();
        assertEquals(expectedError , outContent.toString());
    }

    @AfterEach
    void tearDown() {
        StrongholdCrusader.getAllUsers().clear();
        CoreLoginMenuController.resetDelay();
    }
}