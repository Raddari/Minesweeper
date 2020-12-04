package me.raddari.minesweeper.controller;

public interface GameController {

    void keyPressed(int keycode, int scancode, int modifiers);
    void revealTile(int row, int col);
    void flagTile(int row, int col);
    void generateBombs(int originRow, int originCol);
    int fieldRows();
    int fieldColumns();

}
