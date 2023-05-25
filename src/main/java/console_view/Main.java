package console_view;

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
                400, 400, "BadLands", 8,
                new Location[] {new Location(5, 5), new Location(5, 350), new Location(350, 350), new Location(200, 200),
                        new Location(100, 100), new Location(300, 100), new Location(100, 300), new Location(350, 4)});
        map.getCell(0, 0).setNaturalEntityType(NaturalEntityType.OLIVE_TREE);
        for (int x = 30; x < 400; x++) {
            for (int y = 30; y < 390; y++) {
                map.getCell(x, y).setType(LandType.DENSEMEADOW);
            }
        }
        StrongholdCrusader.getAllStaticMaps().clear();
        StrongholdCrusader.addStaticMap(map);
        control.pushData();
        ///////////////////////////////
        controller.run();
        controller.pushData();
    }
}