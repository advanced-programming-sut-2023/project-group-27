import controller.Controller;
import controller.controller.Utilities;
import model.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String [] args) throws IOException {
        Utilities.init();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);
        controller.fetchData();
        ///////////////////////////////
        Controller control = new Controller(scanner);
        GameMap map = new GameMap(
                800, 800, "BadLands", 8,
                new Location[] {new Location(5, 5), new Location(5, 750), new Location(750, 750), new Location(400, 400),
                        new Location(200, 200), new Location(600, 200), new Location(200, 600), new Location(700, 4)});
        map.getCell(0, 0).setNaturalEntityType(NaturalEntityType.OLIVE_TREE);
        StrongholdCrusader.getAllStaticMaps().clear();
        StrongholdCrusader.addStaticMap(map);
        control.pushData();
        ///////////////////////////////
        controller.run();
        controller.pushData();
    }
}