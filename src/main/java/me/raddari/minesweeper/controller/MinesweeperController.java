package me.raddari.minesweeper.controller;

import me.raddari.minesweeper.game.Minesweeper;
import org.jetbrains.annotations.NotNull;

import java.awt.event.MouseEvent;

public class MinesweeperController implements ControlListener {

    private final Minesweeper minesweeper;

    public MinesweeperController(@NotNull Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
    }

    @Override
    public void tilePressed(int button, int modifiersEx, int row, int col) {
        if (button == MouseEvent.BUTTON1) {
            if (!minesweeper.hasGeneratedBombs()) {
                minesweeper.generateBombs(row, col);
            }
            minesweeper.revealTile(row, col);
        }
        if (button == MouseEvent.BUTTON3) {
            minesweeper.flagTile(row, col);
        }
    }

}
