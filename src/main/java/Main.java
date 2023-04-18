import controller.Controller;

import java.util.Scanner;

public class Main {
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        controller.openFile();
        controller.run(scanner);
        controller.saveFile();
    }
}
