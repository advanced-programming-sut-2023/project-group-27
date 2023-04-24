import controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String [] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);
        controller.fetchData();
        controller.run();
        controller.pushData();
    }
}
