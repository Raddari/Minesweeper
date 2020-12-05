package me.raddari.minesweeper.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.annotation.Testable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Testable
class StandardGameTest {

    StandardGame standardGame;

    @BeforeEach
    private void resetGame() {
        standardGame = new StandardGame(5, 5, 10);
    }

    @ParameterizedTest
    @MethodSource("coordsProvider")
    void generateBombsHasSafezone(int row, int col) {
        final var dim = 10;
        final var bombs = 91;
        standardGame = new StandardGame(dim, dim, bombs);
        standardGame.generateBombs(row, col);
        standardGame.forEachNeighbour(row, col, tile -> assertFalse(tile.isBomb()));
    }

    @Test
    void tooManyBombs() {
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new StandardGame(4, 4, 8));
        assertEquals("Integer out of range", exception.getMessage());
    }

    @Test
    void flagTile() {
        standardGame.flagTile(0, 0);
        assertTrue(standardGame.tileAt(0, 0).isFlagged());
        standardGame.flagTile(0, 0);
        assertFalse(standardGame.tileAt(0, 0).isFlagged());
    }

    @Test
    void incrementTile() {
        assertEquals(0, standardGame.tileAt(0, 0).getValue());
        standardGame.tileAt(0, 0).incrementValue();
        assertEquals(1, standardGame.tileAt(0, 0).getValue());
    }

    private static Stream<Arguments> coordsProvider() {
        return Stream.of(
                arguments(0, 0),
                arguments(0, 9),
                arguments(9, 0),
                arguments(9, 9),
                arguments(4, 4)
        );
    }

}
