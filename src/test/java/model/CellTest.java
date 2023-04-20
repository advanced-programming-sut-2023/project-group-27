package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell = new Cell();
    @BeforeEach
    void setUp() {
    }

    @Test
    void firstTest() {
        assertEquals(3 ,cell.isPassable(cell));
    }

    @AfterEach
    void tearDown() {
    }
}