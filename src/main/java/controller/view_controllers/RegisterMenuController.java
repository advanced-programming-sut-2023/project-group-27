package controller.view_controllers;

import controller.controller.CoreRegisterMenuController;
import model.StrongholdCrusader;
import view.RegisterMenu;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

import static controller.controller.Utilities.randomSlogan;

public class RegisterMenuController {
    CoreRegisterMenuController coreController;
    RegisterMenu menu;

    public RegisterMenuController(CoreRegisterMenuController coreController, Scanner scanner) {
        this.coreController = coreController;
        this.menu = new RegisterMenu(this, scanner);
    }

    public RegisterMenu getMenu() {
        return menu;
    }

    public String createUser(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map<String, String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) {
            return "Do not determine same field more than once!\n";
        }
        if (!args.containsKey("u") || !args.containsKey("p") ||
                !args.containsKey("n") || !args.containsKey("email")) {
            return "Do not leave username, password, nickname or email as blank!\n";
        }
        String username = args.get("u");
        String password = args.get("p");
        String nickname = args.get("n");
        String email = args.get("email");
        String slogan = args.get("s");
        if (slogan == null) slogan = "";

        String result = "";
        if (slogan.equals("random")) {
            slogan = randomSlogan();
            menu.showInformation("Your slogan is \"" + slogan + "\"\n");
        }
        if (password.equals("random")) {
            password = controller.controller.Utilities.randomPassword();
            do {
                menu.showInformation("Your password is \"" + password + "\"\n");
            } while (menu.confirm("Please re-enter your password here:", password));
        }
        String error = coreController.initializeUser(username, password, email, nickname, slogan);
        if (error != null) {
            return error;
        }
        StringBuilder questions = new StringBuilder();
        int index = 0;
        for (String securityQuestion : StrongholdCrusader.getSecurityQuestions()) {
            index++;
            questions.append(index).append(". ").append(securityQuestion).append("\n");
        }
        menu.showInformation("Pick your security question:\n" + questions);
        String choice;
        while (true) {
            choice = menu.fetchAnswer();
            Map<String, String> answerArgs = Utilities.extractOptionsFromString(choice);
            if (answerArgs == null) {
                menu.showInformation("Do not determine same argument more than once!");
                continue;
            }
            if (!answerArgs.containsKey("q")) {
                menu.showInformation("please choose your security question");
                continue;
            }
            if (!answerArgs.containsKey("a")) {
                menu.showInformation("please provide answer to your chosen security question");
                continue;
            }
            if (!answerArgs.containsKey("c")) {
                menu.showInformation("please confirm your answer");
                continue;
            }
            if (answerArgs.keySet().size() > 3) {
                menu.showInformation("extra arguments has been provided. What should I do with them? :/");
                continue;
            }
            String QNum = answerArgs.get("q");
            if (!QNum.matches("\\d+") || Integer.parseInt(QNum) >
                    StrongholdCrusader.getSecurityQuestions().size() ||
                    Integer.parseInt(QNum) == 0) {
                menu.showInformation("wtf bro your choice is invalid");
                continue;
            }
            String answer = answerArgs.get("a");
            String answerConfirmation = answerArgs.get("c");
            if (!answerConfirmation.equals(answer)) {
                menu.showInformation("your answer ans it's confirmation doesn't match");
                continue;
            }
            error = coreController.finalizeUser(StrongholdCrusader.getSecurityQuestions()
                    .get(Integer.parseInt(QNum)-1), answer);
            if (error != null) {
                return error;
            }
            break;
        }
        return "User registered successfully :)\n";
    }
}
