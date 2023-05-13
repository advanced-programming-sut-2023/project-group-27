package model;

import controller.controller.Utilities;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;
import model.task.Fight;
import model.task.Move;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MatchTest {
    GameMap map;
    User user1, user2;
    Match match;
    Man man1 , man2;
    @BeforeEach
    void setUp() {
        Location[] locations = new Location[] {new Location(1, 1), new Location(9, 9)};
        this.map = new GameMap(15, 15, "myMap", 100, locations);
        this.user1 = new User("user1", "Password@1", "user",
                "user", "user@u.com", "a", "b");
        user1.setMonarchy(new Monarchy(user1, MonarchyColorType.BLACK, map, locations[0]));
        user2 = new User("user2", "Password@1", "user",
                "user", "user@u.com", "a", "b");
        user2.setMonarchy(new Monarchy(user2, MonarchyColorType.BLACK, map, locations[1]));
        User[] users = new User[]{user1 , user2};
        this.match = new Match(map, Arrays.stream(users).toList());
        man1 = Utilities.getNewMan(SoldierType.SWORDSMAN , user1);
        man2 = Utilities.getNewMan(SoldierType.PIKEMAN , user2);
        map.getCell(3 , 3).addMan(man1);
        map.getCell(5 , 5).addMan(man2);
    }

    @Test
    @DisplayName("Task update")
    void updateTask() {
        Fight fight = new Fight(map, (Fightable) man2, man1);
        match.addTask(fight);
        Move move = new Move(map, man1, 7, 7);
        match.addTask(move);
        ((Soldier) man1).setTask(move);
        ((Soldier) man2).setTask(fight);
        match.nextTurn();
        match.nextTurn();
        assertTrue(((Soldier) man1).getTask() instanceof Fight);
    }
}