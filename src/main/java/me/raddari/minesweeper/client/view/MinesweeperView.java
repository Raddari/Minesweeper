package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.controller.MinesweeperController;
import me.raddari.minesweeper.game.Minesweeper;
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

    public MinesweeperView(@NotNull Minesweeper minesweeper, int width, int height) {
        textureManager = preCacheTextures(TextureManager.create());

        var frame = new JFrame("Minesweeper");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var controller = new MinesweeperController(minesweeper);
        minefieldPanel = new MinefieldPanel(minesweeper, controller, textureManager);
        frame.add(minefieldPanel);
        frame.pack();

        frame.setVisible(true);
    }

    private static TextureManager preCacheTextures(TextureManager manager) {
        final var textures = new ArrayList<String>();
        Collections.addAll(textures,
                "tile.bad_bomb", "tile.bad_flag", "tile.bomb", "tile.flag", "tile.revealed", "tile.unrevealed");
        for (var i = 1; i <= 8; i++) {
            textures.add("tile.n" + i);
        }
        for (final var texPath : textures) {
            manager.get(texPath);
        }
        return manager;
    }

}
