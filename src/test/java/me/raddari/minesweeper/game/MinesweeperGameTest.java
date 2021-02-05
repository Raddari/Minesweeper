package me.raddari.minesweeper.game;

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
class MinesweeperGameTest {

    MinesweeperGame minesweeper;

    @BeforeEach
    private void resetGame() {
        minesweeper = new MinesweeperGame(5, 5, 10);
    }

    @ParameterizedTest
    @MethodSource("coordsProvider")
    void generateBombsHasSafezone(int row, int col) {
        final var dim = 10;
        final var bombs = 91;
        minesweeper = new MinesweeperGame(dim, dim, bombs);
        minesweeper.generateBombs(row, col);
        minesweeper.forEachNeighbour(row, col, tile -> assertFalse(tile.isBomb()));
    }

    @Test
    void tooManyBombs() {
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new MinesweeperGame(4, 4, 8));
        assertEquals("Integer out of range", exception.getMessage());
    }

    @Test
    void flagTile() {
        minesweeper.flagTile(0, 0);
        assertTrue(minesweeper.tileAt(0, 0).isFlagged());
        minesweeper.flagTile(0, 0);
        assertFalse(minesweeper.tileAt(0, 0).isFlagged());
    }

    @Test
    void incrementTile() {
        assertEquals(0, minesweeper.tileAt(0, 0).getValue());
        minesweeper.tileAt(0, 0).incrementValue();
        assertEquals(1, minesweeper.tileAt(0, 0).getValue());
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
