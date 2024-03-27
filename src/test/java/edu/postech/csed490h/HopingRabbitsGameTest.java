package edu.postech.csed490h;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HopingRabbitsGameTest {

    @Test
    void testGetState() {
        var game = new HopingRabbitsGame(2);
        assertEquals("xx_oo", game.getState());
    }

    @Test
    void testMove() {
        var game = new HopingRabbitsGame(2);
        assertTrue(game.move(Rabbit.X));
    }

    @Test
    void testIsStuck() {
        var game = new HopingRabbitsGame(2);
        assertFalse(game.isStuck());
    }

    @Test
    void testTwo() {
        var game = new HopingRabbitsGame(2);
        assertEquals("xx_oo", game.getState());
        assertTrue(game.move(Rabbit.X));
        assertEquals("x_xoo", game.getState());
        assertTrue(game.move(Rabbit.O));
        assertEquals("xox_o", game.getState());
        assertTrue(game.move(Rabbit.X));
        assertEquals("xo_xo", game.getState());
        assertTrue(game.move(Rabbit.X));
        assertEquals("_oxxo", game.getState());
        assertTrue(game.move(Rabbit.O));
        assertEquals("o_xxo", game.getState());
        assertTrue(game.isStuck());
    }
}
