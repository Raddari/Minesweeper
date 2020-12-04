package me.raddari.minesweeper.tile;

public final class Tile {

    private boolean isFlagged;
    private boolean isBomb;
    private int value;

    public Tile() {
        isFlagged = false;
        isBomb = false;
        value = 0;
    }

    public void flipFlag() {
        isFlagged = !isFlagged;
    }

    public void makeBomb() {
        isBomb = true;
    }

    public void incrementValue() {
        value++;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public int getValue() {
        return value;
    }

}
