package me.raddari.minesweeper.client;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Minesweeper());
    }

}
