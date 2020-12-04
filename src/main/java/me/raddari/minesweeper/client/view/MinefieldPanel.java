package me.raddari.minesweeper.client.view;

import me.raddari.minesweeper.util.Numbers;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MinefieldPanel extends JPanel {

    private final int fieldRows;
    private final int fieldCols;
    private final JButton[][] fieldTiles;
    private final Map<Integer, JButton> buttonMap;

    public MinefieldPanel(int fieldRows, int fieldCols) {
        this.fieldRows = Numbers.rangeCheck(fieldRows, 1, Integer.MAX_VALUE);
        this.fieldCols = Numbers.rangeCheck(fieldCols, 1, Integer.MAX_VALUE);
        fieldTiles = new JButton[fieldRows][fieldCols];
        buttonMap = new LinkedHashMap<>();

        for (var m = 0; m < fieldRows; m++) {
            for (var n = 0; n < fieldCols; n++) {
                var button = new JButton();
                fieldTiles[m][n] = button;
                buttonMap.put(m * fieldCols + n, button);
            }
        }
    }

    private void buttonPressed(@NotNull JButton button, int buttonRow, int buttonCol) {

    }

    public @NotNull JButton tileAt(int row, int col) {
        Numbers.rangeCheck(row, 0, fieldRows - 1);
        Numbers.rangeCheck(col, 0, fieldCols - 1);
        return buttonMap.get(row * fieldCols + col);
    }

}