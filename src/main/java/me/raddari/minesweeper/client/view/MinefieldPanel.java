package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.controller.GameController;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public final class MinefieldPanel extends JPanel {

    private final GameController controller;
    private final int fieldRows;
    private final int fieldCols;

    public MinefieldPanel(@NotNull GameController controller) {
        this.controller = controller;
        fieldRows = controller.fieldRows();
        fieldCols = controller.fieldColumns();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g.create();
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (var m = 0; m < fieldRows; m++) {
            for (var n = 0; n < fieldCols; n++) {
                if (controller.tileAt(m, n).isBomb()) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(n * 32, m * 32, 32, 32);
                }
                g2d.setColor(Color.WHITE);
                g2d.drawRect(n * 32, m * 32, 32, 32);
            }
        }
    }

}
