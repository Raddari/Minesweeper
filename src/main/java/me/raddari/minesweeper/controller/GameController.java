package me.raddari.minesweeper.controller;

import me.raddari.minesweeper.tile.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface GameController {

    void keyPressed(int keycode, int scancode, int modifiers);

    void revealTile(int row, int col);

    void flagTile(int row, int col);

    void generateBombs(int originRow, int originCol);

    int fieldRows();

    int fieldColumns();

    @NotNull Tile tileAt(int row, int col);

    default void forEachNeighbour(int originRow, int originCol, @NotNull Consumer<Tile> action) {
        final var fieldRows = fieldRows();
        final var fieldCols = fieldColumns();

        var rowMin = Math.max(0, originRow - 1);
        var rowMax = Math.min(fieldRows - 1, originRow + 1);
        var colMin = Math.max(0, originCol - 1);
        var colMax = Math.min(fieldCols - 1, originCol + 1);

        for (var row = rowMin; row <= rowMax; row++) {
            for (var col = colMin; col <= colMax; col++) {
                if (!(row == originRow && col == originCol)) {
                    var tile = tileAt(row, col);
                    action.accept(tile);
                }
            }
        }
    }

}
