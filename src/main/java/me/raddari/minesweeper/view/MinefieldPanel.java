package me.raddari.minesweeper.view;

import me.raddari.minesweeper.util.Numbers;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MinefieldPanel extends JPanel {

    private final int mineRows;
    private final int mineCols;
    private final JButton[][] mineTiles;
    private final Map<Integer, JButton> buttonMap;

    public MinefieldPanel(int mineRows, int mineCols) {
        this.mineRows = Numbers.rangeCheck(mineRows, 1, Integer.MAX_VALUE);
        this.mineCols = Numbers.rangeCheck(mineCols, 1, Integer.MAX_VALUE);
        mineTiles = new JButton[mineRows][mineCols];
        buttonMap = new LinkedHashMap<>();

        for (var m = 0; m < mineRows; m++) {
            for (var n = 0; n < mineCols; n++) {
                var button = new JButton();
                mineTiles[m][n] = button;
                buttonMap.put(m * mineCols + n, button);
            }
        }
    }

    private void buttonPressed(@NotNull JButton button, int buttonRow, int buttonCol) {

    }

    public @NotNull JButton tileAt(int row, int col) {
        Numbers.rangeCheck(row, 0, mineRows - 1);
        Numbers.rangeCheck(col, 0, mineCols - 1);
        return buttonMap.get(row * mineCols + col);
    }

}
