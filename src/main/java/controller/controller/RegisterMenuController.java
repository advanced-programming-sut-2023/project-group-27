package controller.controller;

public class RegisterMenuController {
    public String createUser(
            String username, String password, String passwordConfirmation,
            String email, String nickname, String slogan, String securityQ,
            String securityA) {
        return "User created successfully";
    }

}
