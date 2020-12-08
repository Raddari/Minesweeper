package me.raddari.minesweeper.controller;

import me.raddari.minesweeper.tile.Tile;
import me.raddari.minesweeper.util.Numbers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.awt.Point;
import java.util.Collections;
import java.util.LinkedList;

public final class StandardGame implements GameController {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MIN_ROWS = 4;
    private static final int MAX_ROWS = 200;
    private static final int MIN_COLS = MIN_ROWS;
    private static final int MAX_COLS = MAX_ROWS;
    private final Tile[][] minefield;
    private final int fieldRows;
    private final int fieldCols;
    private final int maxBombs;
    private boolean hasBombs;

    public StandardGame(int rows, int cols, int maxBombs) {
        fieldRows = Numbers.rangeCheck(rows, MIN_ROWS, MAX_ROWS);
        fieldCols = Numbers.rangeCheck(cols, MIN_COLS, MAX_COLS);
        minefield = new Tile[rows][cols];
        // Need to allow a 3x3 space around the clicked tile which is bomb free
        this.maxBombs = Numbers.rangeCheck(maxBombs, 1, rows * cols - 9);
        hasBombs = false;

        for (var row = 0; row < rows; row++) {
            for (var col = 0; col < cols; col++) {
                minefield[row][col] = new Tile();
            }
        }
    }

    public @NotNull Tile tileAt(int row, int col) {
        Numbers.rangeCheck(row, 0, fieldRows - 1);
        Numbers.rangeCheck(col, 0, fieldCols - 1);
        return minefield[row][col];
    }

    @Override
    public void revealTile(int row, int col) {
        var tile = tileAt(row, col);
    }

    @Override
    public void flagTile(int row, int col) {
        var tile = tileAt(row, col);
        tile.flipFlag();
    }

    @Override
    public void generateBombs(int originRow, int originCol) {
        if (hasBombs) {
            throw new IllegalStateException("Minefield already has bombs");
        }
        var pointList = new LinkedList<Point>();
        for (var m = 0; m < fieldRows; m++) {
            for (var n = 0; n < fieldCols; n++) {
                pointList.add(new Point(n, m));
            }
        }
        Collections.shuffle(pointList);

        var origin = new Point(originCol, originRow);
        var remaining = maxBombs;
        while (remaining > 0 && !pointList.isEmpty()) {
            var bombPoint = pointList.pop();
            // Anything with a squared distance > 2 is outside the 3x3 grid centered on this tile
            if (bombPoint.distanceSq(origin) > 2.0) {
                tileAt(bombPoint.y, bombPoint.x).makeBomb();
                --remaining;
                LOGGER.debug("Generated bomb at ({},{}), remaining: {}", bombPoint.x, bombPoint.y, remaining);
                forEachNeighbour(bombPoint.y, bombPoint.x, Tile::incrementValue);
            }
        }
        hasBombs = true;
    }

    @Override
    public int fieldRows() {
        return fieldRows;
    }

    @Override
    public int fieldColumns() {
        return fieldCols;
    }

}
