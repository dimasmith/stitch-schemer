package net.anatolich.stitcher.image;

import lombok.Value;

import java.awt.*;

/**
 * Wrapper of int array containing all color data of image.
 */
@Value
public class ImageData {

    private final int width;
    private final int height;

    public Color getColor(int x, int y) {
        return Color.BLACK;
    }

    public void setColor(int x, int y, Color color) {

    }
}
