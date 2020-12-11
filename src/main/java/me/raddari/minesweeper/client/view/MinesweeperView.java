package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.controller.GameController;
import me.raddari.minesweeper.util.TextureManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

public final class MinesweeperView {

    private static final Logger LOGGER = LogManager.getLogger();
    private final TextureManager textureManager;
    private final MinefieldPanel minefieldPanel;

    public MinesweeperView(@NotNull GameController controller, int width, int height) {
        textureManager = TextureManager.create();
        preCacheTextures();

        var frame = new JFrame("Minesweeper");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        minefieldPanel = new MinefieldPanel(controller, textureManager);
        frame.add(minefieldPanel);
        frame.pack();

        frame.setVisible(true);
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
