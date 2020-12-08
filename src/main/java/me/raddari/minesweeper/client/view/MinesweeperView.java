package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.controller.GameController;
import me.raddari.minesweeper.util.TextureManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public final class MinesweeperView implements KeyListener {

    private final GameController controller;
    private final TextureManager textureManager;
    private final JFrame frame;
    private MinefieldPanel minefieldPanel;

    public MinesweeperView(@NotNull GameController controller, int width, int height) {
        this.controller = controller;
        textureManager = TextureManager.create();
        preCacheTextures();

        frame = new JFrame("Minesweeper");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        var rows = controller.fieldRows();
        var cols = controller.fieldColumns();
        minefieldPanel = new MinefieldPanel(controller);
        frame.add(minefieldPanel);

        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyLocation()) {
            case KeyEvent.VK_W -> {

            }
            case KeyEvent.VK_A -> {

            }
            case KeyEvent.VK_S -> {

            }
            case KeyEvent.VK_D -> {

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }

    private void preCacheTextures() {
        var textures = new ArrayList<String>();
        Collections.addAll(textures, "bad_bomb", "bad_flag", "bomb", "flag", "revealed", "tile");
        for (var i = 1; i <= 8; i++) {
            textures.add("n" + i);
        }
        textureManager.loadAll(textures);
    }

}
