package net.anatolich.stitcher.color;

import net.anatolich.stitcher.image.ImageData;

/**
 * Analyses image and gathers colors in it.
 */
public class ColorDetector {

    public ColorData analyzeImageColors(ImageData image) {
        final ColorData.Builder colorData = ColorData.buildColorData();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colorData.withColor(image.getColor(x, y));
            }
        }
        return colorData.build();
    }
}
