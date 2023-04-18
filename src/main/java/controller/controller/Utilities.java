package controller.controller;

public class Utilities {
    public static String validatePassword(String newPassword) {
        if (newPassword.length() < 6) {
            return "Password is too short";
        }
        if (!newPassword.matches(".*[0-9].*]") || !newPassword.matches(".*[A-Z].*]") || !newPassword.matches(".*[a-z].*]")) {
            return "Password must contain at least one uppercase letter, one lowercase letter and one digit";
        }
        if (newPassword.matches("[0-9a-zA-Z_]+")) {
            return "Password must contain at least one special character";
        }
        return null;
    }
}
