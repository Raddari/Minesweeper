package me.raddari.minesweeper.client;

import me.raddari.minesweeper.client.view.MinesweeperView;
import me.raddari.minesweeper.game.MinesweeperGame;

import javax.swing.SwingUtilities;

public final class Main implements Runnable {

    Main() {
    }

    @Override
    public void run() {
        final var minesweeper = new MinesweeperGame(16, 16, 50);
        final var view = new MinesweeperView(minesweeper, 1920, 1080);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

}
