package me.raddari.minesweeper.util;

import org.jetbrains.annotations.NotNull;

public interface ResourceManager<T> {

    @NotNull T load(@NotNull String path);

}
