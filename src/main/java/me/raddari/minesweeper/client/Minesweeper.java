package me.raddari.minesweeper.client;

import me.raddari.minesweeper.client.view.MinesweeperView;
import me.raddari.minesweeper.controller.GameController;
import me.raddari.minesweeper.controller.StandardGame;

import javax.swing.SwingUtilities;

public final class Minesweeper implements Runnable {

    Minesweeper() {
    }

    @Override
    public void run() {
        final GameController controller = new StandardGame(16, 16, 50);
        final MinesweeperView view = new MinesweeperView(controller, 1920, 1080);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Minesweeper());
    }

}
