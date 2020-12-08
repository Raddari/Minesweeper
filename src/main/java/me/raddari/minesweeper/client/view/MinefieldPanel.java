package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.controller.GameController;
import me.raddari.minesweeper.tile.Tile;
import me.raddari.minesweeper.util.TextureManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.function.Supplier;

public final class MinefieldPanel extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 16;
    private final transient GameController controller;
    private final transient TextureManager textureManager;
    private final int fieldRows;
    private final int fieldCols;

    public MinefieldPanel(@NotNull GameController controller, @NotNull TextureManager textureManager) {
        this.controller = controller;
        this.textureManager = textureManager;
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
                final var scaledWidth = (float) getWidth() / DEFAULT_COLS;
                final var scaledHeight = (float) getHeight() / DEFAULT_ROWS;
                final var w = 32;
                final var h = 32;

                var tile = controller.tileAt(m, n);
                drawTile(g2d, tile, m, n, w, h);

                g2d.setColor(Color.WHITE);
                g2d.drawRect(w * n, h * m, w, h);
            }
        }
    }

    private void drawTile(Graphics2D g2d, Tile tile, int row, int col, int width, int height) {
        g2d.drawImage(textureManager.get("tile.revealed"), col * width, row * height, width, height, this);
        Supplier<Image> texture = () -> {
            if (tile.isBomb()) {
                return textureManager.get("tile.bomb");
            }
            if (tile.isFlagged()) {
                return textureManager.get("tile.flag");
            }
            if (tile.getValue() == 0) {
                return textureManager.get("tile.revealed");
            }
            return textureManager.get("tile.n" + tile.getValue());
        };

        g2d.drawImage(texture.get(), col * width, row * height, width, height, this);
    }

}
