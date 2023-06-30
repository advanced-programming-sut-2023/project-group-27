package graphics_view.view;

import controller.Controller;
import controller.controller.Utilities;
import model.*;

import java.io.IOException;

public class MapLauncher {
    public static void main(String[] args) throws IOException {
        Utilities.init();
        Controller.fetchData();
        GameMap map = new GameMap(
                200, 200, "HollyLands", 4,
                new Location[] {new Location(5, 5), new Location(5, 150), new Location(150, 150), new Location(150, 5)});
        map.getCell(0, 0).setNaturalEntityType(NaturalEntityType.OLIVE_TREE);
        for (int x = 30; x < 45; x++) {
            for (int y = 30; y < 45; y++) {
                map.getCell(x, y).setType(LandType.DENSEMEADOW);
            }
        }
        StrongholdCrusader.getAllStaticMaps().clear();
        StrongholdCrusader.addStaticMap(map);
        Controller.pushData();
    }
}
