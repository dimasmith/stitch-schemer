package net.anatolich.stitcher.file;

import net.anatolich.stitcher.image.ImageData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Read image from file.
 */
public class ImageReader {

    public ImageData readImageFile(Path path) {
        final BufferedImage image = readImage(path);
        return mapImage(image);
    }

    private ImageData mapImage(BufferedImage image) {
        ImageData imageData = new ImageData(image.getWidth(), image.getHeight());
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageData.setColor(x, y, new Color(image.getRGB(x, y));
            }
        }
        return imageData;
    }

    private BufferedImage readImage(Path path) {
        final BufferedImage image;
        try {
            image = ImageIO.read(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while reading file %s", path.toUri()));
        }
        return image;
    }
}
