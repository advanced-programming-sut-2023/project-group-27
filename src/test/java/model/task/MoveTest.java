package model.task;

import controller.controller.Utilities;
import model.GameMap;
import model.NaturalEntityType;
import model.User;
import model.man.Man;
import model.man.SoldierType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    private GameMap map;
    private Man man;

    @BeforeEach
    void setUp() {
        User user = new User(
                "test",
                "Password@1",
                "test",
                "test",
                "test",
                "a",
                "b");
        this.map = new GameMap(50, 50, "myMap", capacity);
        this.man = Utilities.getNewMan(SoldierType.ARCHER, user);
        map.getCell(0, 0).addMan(man);
        for (int i = 1; i < 50; i+= 2) {
            for (int j = ((i % 4 == 3) ? 25 : 0) ; j < ((i % 4 == 3) ? 50 : 25); j++) {
                map.getCell(i, j).setNaturalEntityType(NaturalEntityType.ROCK_SOUTH);
            }
        }
    }

    @Test
    void simpleMoveTest() {
        Move moveTask = new Move(this.map, this.man, 0, 49);
        double speed = man.getMovementSpeed();
        double yLocation = 0;
        while (moveTask.isValid()) {
            moveTask.run();
            yLocation += speed;
            int yInt = (int) yLocation;
            assertEquals(yInt, man.getLocation().y);
        }
    }

    @Test
    void MoveTest() {
        Move moveTask = new Move(this.map, this.man, 48, 0);
        int expectedTurns = 0;
        while (moveTask.isValid()) {
            expectedTurns++;
            moveTask.run();
        }
        assertEquals(81, expectedTurns);
        assertEquals(48, man.getLocation().x);
        assertEquals(0, man.getLocation().y);
    }

    @Test
    void simplePatrolTest() {
        Patrol patrolTask = new Patrol(this.map, this.man, 1, 25, 1, 49);
        for (int i = 0; i < 18; i++) {
            patrolTask.run();
        }
        assertEquals(man.getLocation().x, 1);
        assertEquals(man.getLocation().y, 25);
        for (int i = 0; i < 16; i++) {
            patrolTask.run();
        }
        assertEquals(man.getLocation().x, 1);
        assertEquals(man.getLocation().y, 49);
        for (int i = 0; i < 16; i++) {
            patrolTask.run();
        }
        assertEquals(man.getLocation().x, 1);
        assertEquals(man.getLocation().y, 25);
    }
}