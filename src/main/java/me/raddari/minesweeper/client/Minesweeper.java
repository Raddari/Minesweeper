package me.raddari.minesweeper.client;

import me.raddari.minesweeper.controller.GameController;
import me.raddari.minesweeper.view.MinesweeperView;

public final class Minesweeper implements Runnable {

    private MinesweeperView view;
    private GameController gameController;

    Minesweeper() {
    }

    @Override
    public void run() {
        view = new MinesweeperView(gameController, 1920, 1080);
    }

}
