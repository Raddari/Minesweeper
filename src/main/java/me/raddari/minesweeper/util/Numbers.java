package me.raddari.minesweeper.util;

public final class Numbers {

    public static int rangeCheck(int a, int lower, int upper) {
        if (a < lower || a > upper) {
            throw new IllegalArgumentException("Integer out of range");
        }
        return a;
    }

    private Numbers() {
    }

}
