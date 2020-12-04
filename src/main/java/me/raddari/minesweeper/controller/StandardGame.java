package me.raddari.minesweeper.controller;

import me.raddari.minesweeper.tile.Tile;
import me.raddari.minesweeper.util.Numbers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StandardGame implements GameController {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Tile[][] minefield;
    private final int mineRows;
    private final int mineCols;

    public StandardGame(int rows, int cols) {
        minefield = new Tile[rows][cols];
        mineRows = rows;
        mineCols = cols;
    }

    private void boundCheck(int row, int col) {
        Numbers.rangeCheck(row, 0, mineRows - 1);
        Numbers.rangeCheck(col, 0, mineCols - 1);
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
    public int mineRows() {
        return mineRows;
    }

    @Override
    public int mineColumns() {
        return mineCols;
    }

}
