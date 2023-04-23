package controller.view_controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import view.enums.RegisterMenuRegexes;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class RegisterMenuControllerTest {
    @ParameterizedTest
    @CsvSource({
            "'user create -u user -u user', 'Do not determine same field more than once!\n'",
            "'user create -u user -p pass', 'Do not leave username, password, nickname or email as blank!\n'",
    })
    void createUser(String command, String expectedError) {
        Matcher matcher = RegisterMenuRegexes.RAW_REGISTER.getMatcher(command);
        RegisterMenuController controller = new RegisterMenuController();
        String output = controller.createUser(matcher);
        assertEquals(expectedError, output);
    }
}