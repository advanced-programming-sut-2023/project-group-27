package controller.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoreRegisterMenuControllerTest {

    @Test
    void initializeAndFinalizeUser() {
        CoreRegisterMenuController coreController = new CoreRegisterMenuController();
        String output;
        output = coreController.initializeUser("mobin@", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Invalid username format", output);
        output = coreController.initializeUser("mobin", "password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Password must contain at least one " +
                "uppercase letter, one lowercase letter and one digit", output);
        output = coreController.initializeUser("mobin", "Pass1wAord@1", "mmm.com", "nickname", "slogan");
        assertEquals("Invalid email format", output);
        output = coreController.initializeUser("mobin", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertNull(output);
        coreController.finalizeUser("Q", "A");
        output = coreController.initializeUser("mobin", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Username already exists", output);
        output = coreController.initializeUser("mobin2", "Password@1", "mo@gm.com", "nickname", "slogan");
        assertEquals("Email already exists", output);
    }
}