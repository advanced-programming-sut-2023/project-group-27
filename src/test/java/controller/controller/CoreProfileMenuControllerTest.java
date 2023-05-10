package controller.controller;

import model.StrongholdCrusader;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CoreProfileMenuControllerTest {
    private CoreProfileMenuController coreController;

    @BeforeEach
    void setUp() {
        User user1 = new User("test", "Password@1", "test", "test", "test@test.com", "test", "test");
        user1.setHighScore(1000);
        StrongholdCrusader.addUser(user1);
        User user2 = new User("test2", "Password@1", "test2", "test2", "test2@test2.com", "test2", "test2");
        user2.setHighScore(2000);
        StrongholdCrusader.addUser(user2);
        User user3 = new User("test3", "Password@1", "test3", "test3", "test3@test3.com", "test3", "test3");
        user3.setHighScore(3000);
        StrongholdCrusader.addUser(user3);

        StrongholdCrusader.setLoggedInUser(StrongholdCrusader.getUserByName("test"));
        coreController = new CoreProfileMenuController(new Scanner(""));
    }

    @Test
    void changePassword() {
        assertEquals("Current password is incorrect!\n", coreController.changePasswordPrep("wrongPassword", "newPassword"));
        assertEquals("New password can not be the same as old password\n", coreController.changePasswordPrep("Password@1", "Password@1"));
        assertNull(coreController.changePasswordPrep("Password@1", "Password@2"));
        coreController.changePassword("Password@2");
        assertTrue(StrongholdCrusader.getLoggedInUser().isPasswordCorrect("Password@2"));
    }

    @Test
    void changeUsername() {
        assertEquals("Username can not be the same as old username\n", coreController.changeUsername("test"));
        assertEquals("empty field\n", coreController.changeUsername(""));
        assertEquals("Invalid username format\n", coreController.changeUsername("test@"));
        coreController.changeUsername("test2");
        assertEquals("test2", StrongholdCrusader.getLoggedInUser().getUsername());
    }

    @Test
    void changeNickname() {
        coreController.changeNickname("test2");
        assertEquals("test2", StrongholdCrusader.getLoggedInUser().getNickname());
    }

    @Test
    void isPasswordValid() {
        assertTrue(coreController.isPasswordValid("Password@1"));
        assertFalse(coreController.isPasswordValid("wrongPassword"));
    }

    @Test
    void showRank() {
        assertEquals(3, coreController.showRank());
        StrongholdCrusader.setLoggedInUser(StrongholdCrusader.getUserByName("test2"));
        coreController = new CoreProfileMenuController(new Scanner(""));
        assertEquals(2, coreController.showRank());
        StrongholdCrusader.setLoggedInUser(StrongholdCrusader.getUserByName("test3"));
        coreController = new CoreProfileMenuController(new Scanner(""));
        assertEquals(1, coreController.showRank());
    }

    @Test
    void showProfile() {
        String expected = "Username: test\n" +
                "Nickname: test\n" +
                "Rank: 3\n" +
                "High Score: 1000\n" +
                "Slogan: test\n" +
                "Email: test@test.com\n";
        assertEquals(expected, coreController.showProfile());
    }

    @Test
    void changeEmail() {
        assertEquals("empty field\n", coreController.changeEmail(""));
        assertEquals("Invalid email format\n", coreController.changeEmail("test"));
        assertEquals("Email already exists\n", coreController.changeEmail("test3@test3.com"));
        coreController.changeEmail("test1@test1.com");
        assertEquals("test1@test1.com", StrongholdCrusader.getLoggedInUser().getEmail());
    }
}