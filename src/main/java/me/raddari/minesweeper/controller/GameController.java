package me.raddari.minesweeper.controller;

import me.raddari.minesweeper.tile.Tile;
import org.jetbrains.annotations.NotNull;

public interface GameController {

    void keyPressed(int keycode, int scancode, int modifiers);
    void revealTile(int row, int col);
    void flagTile(int row, int col);
    void generateBombs(int originRow, int originCol);
    int fieldRows();
    int fieldColumns();
    @NotNull Tile tileAt(int row, int col);

}
