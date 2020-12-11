package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.controller.GameController;
import me.raddari.minesweeper.util.TextureManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

public final class MinesweeperView implements MouseListener {

    private static final Logger LOGGER = LogManager.getLogger();
    private final TextureManager textureManager;
    private final MinefieldPanel minefieldPanel;

    public MinesweeperView(@NotNull GameController controller, int width, int height) {
        textureManager = TextureManager.create();
        preCacheTextures();

        var frame = new JFrame("Minesweeper");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        minefieldPanel = new MinefieldPanel(controller, textureManager);
        frame.add(minefieldPanel);

        frame.addMouseListener(this);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var mX = e.getX();
        var mY = e.getY();

        LOGGER.debug("CLICK: ({},{})", mX, mY);
        minefieldPanel.mouseClicked(mX, mY, e.getModifiersEx());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Unused
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Unused
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Unused
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Unused
    }

    private void preCacheTextures() {
        var textures = new ArrayList<String>();
        Collections.addAll(textures,
                "tile.bad_bomb", "tile.bad_flag", "tile.bomb", "tile.flag", "tile.revealed", "tile.unrevealed");
        for (var i = 1; i <= 8; i++) {
            textures.add("tile.n" + i);
        }
        textureManager.loadAll(textures);
    }

}
