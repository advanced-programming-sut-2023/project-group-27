package controller.view_controllers;

import controller.controller.CoreRegisterMenuController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import console_view.enums.RegisterMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class RegisterMenuControllerTest {
    @ParameterizedTest
    @CsvSource({
            "'user create -u user -u user', 'Do not determine same field more than once!\n'",
            "'user create -u user -p pass', 'Do not leave username, password, nickname or email as blank!\n'",
    })
    void createUser(String command, String expectedError) {
        Matcher matcher = RegisterMenuRegexes.RAW_REGISTER.getMatcher(command);
        RegisterMenuController controller =
                new CoreRegisterMenuController(new Scanner("")).getRegisterController();
        String output = controller.createUser(matcher);
        assertEquals(expectedError, output);
    }
}