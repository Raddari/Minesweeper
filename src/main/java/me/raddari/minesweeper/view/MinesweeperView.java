package me.raddari.minesweeper.view;

import me.raddari.minesweeper.controller.GameController;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public final class MinesweeperView implements MouseInputListener, MouseWheelListener, KeyListener {

    private final GameController gameController;
    private final JFrame frame;
    private MinefieldPanel minefieldPanel;

    public MinesweeperView(@NotNull GameController gameController, int width, int height) {
        this.gameController = gameController;

        frame = new JFrame("Minesweeper");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        var rows = gameController.mineRows();
        var cols = gameController.mineColumns();
        minefieldPanel = new MinefieldPanel(rows, cols);
        minefieldPanel.setLayout(new GridLayout(rows, cols));
        frame.add(minefieldPanel);

        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        frame.addMouseWheelListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameController.keyPressed(e.getKeyCode(), e.getKeyLocation(), e.getModifiersEx());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

}
