package me.raddari.minesweeper.controller;

import me.raddari.minesweeper.tile.Tile;
import me.raddari.minesweeper.util.Numbers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StandardGame implements GameController {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Tile[][] minefield;
    private final int fieldRows;
    private final int fieldCols;
    private final int maxBombs;

    public StandardGame(int rows, int cols, int maxBombs) {
        minefield = new Tile[rows][cols];
        fieldRows = rows;
        fieldCols = cols;
        this.maxBombs = maxBombs;
    }

    private void boundCheck(int row, int col) {
        Numbers.rangeCheck(row, 0, fieldRows - 1);
        Numbers.rangeCheck(col, 0, fieldCols - 1);
    }

    private Tile tileAt(int row, int col) {
        boundCheck(row, col);
        return minefield[row][col];
    }

    @Override
    public void keyPressed(int keycode, int scancode, int modifiers) {

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
