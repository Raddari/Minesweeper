package me.raddari.minesweeper.game;

import me.raddari.minesweeper.tile.Tile;
import org.jetbrains.annotations.NotNull;

public interface Minesweeper {

    void revealTile(int row, int col);

    boolean isRevealed(int row, int col);

    boolean isRevealed(@NotNull Tile tile);

    void flagTile(int row, int col);

    void generateBombs(int originRow, int originCol);

    boolean hasGeneratedBombs();

    int fieldRows();

    int fieldColumns();

    @NotNull Tile tileAt(int row, int col);

}
