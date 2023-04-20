import controller.Controller;
import controller.controller.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String [] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        controller.fetchData();
        controller.run(scanner);
        controller.pushData();
    }
}
