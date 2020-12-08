package me.raddari.minesweeper.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TextureManager implements ResourceManager<Image>  {

    private static final String TEXTURES_DIR = "assets/textures/";
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, Image> textureMap;

    private TextureManager() {
        textureMap = new LinkedHashMap<>();
    }

    public static @NotNull TextureManager create() {
        return new TextureManager();
    }

    @Override
    public @NotNull Image get(@NotNull String path) {
        if (textureMap.containsKey(path)) {
            return textureMap.get(path);
        }
        var imgDir = TEXTURES_DIR + path.replace('.', '/') + ".png";
        LOGGER.debug("Loading texture {}", imgDir);
        var imgStream = getClass().getClassLoader().getResourceAsStream(imgDir);
        if (imgStream == null) {
            throw new IllegalArgumentException("No such texture: " + imgDir);
        }

        try {
            var img = ImageIO.read(imgStream);
            textureMap.put(path, img);
            return img;
        } catch (IOException e) {
            LOGGER.error("Texture not found", e);
            throw new IllegalArgumentException(e);
        }
    }

}
