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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public final class MinefieldPanel extends JPanel implements MouseListener {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 16;
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = TILE_WIDTH;
    private final transient GameController controller;
    private final transient TextureManager textureManager;
    private final int fieldRows;
    private final int fieldCols;

    public MinefieldPanel(@NotNull GameController controller, @NotNull TextureManager textureManager) {
        this.controller = controller;
        this.textureManager = textureManager;
        fieldRows = controller.fieldRows();
        fieldCols = controller.fieldColumns();
        addMouseListener(this);
    }

    private boolean withinField(int x, int y) {
        return 0 <= x && x < fieldCols * TILE_WIDTH && 0 <= y && y < fieldRows * TILE_HEIGHT;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        final var x = e.getX();
        final var y = e.getY();
        final var modifiers = e.getModifiersEx();

        if (!withinField(x, y)) {
            return;
        }

        var tileRow = y / fieldRows;
        var tileCol = x / fieldCols;
        LOGGER.debug("TILE: ({},{})", tileCol, tileRow);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g.create();
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (var m = 0; m < fieldRows; m++) {
            for (var n = 0; n < fieldCols; n++) {
                final var w = TILE_WIDTH;
                final var h = TILE_HEIGHT;

                var tile = controller.tileAt(m, n);
                for (var texture : tileImagesFor(tile)) {
                    g2d.drawImage(texture, w * n, h * m, w, h, this);
                }

                g2d.setColor(Color.WHITE);
                g2d.drawRect(w * n, h * m, w, h);
            }
        }
    }

    private List<Image> tileImagesFor(Tile tile) {
        var images = new ArrayList<Image>();
        if (!controller.isRevealed(tile)) {
            images.add(textureManager.get("tile.unrevealed"));
            if (tile.isFlagged()) {
                images.add(textureManager.get("tile.flag"));
            }

        } else {
            images.add(textureManager.get("tile.revealed"));

            if (!tile.isBomb()) {
                if (tile.getValue() > 0) {
                    images.add(textureManager.get(String.format("tile.n%s", tile.getValue())));
                }
            } else {
                images.add(textureManager.get("tile.bomb"));
            }
        }
        return images;
    }

}
