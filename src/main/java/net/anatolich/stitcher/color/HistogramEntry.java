package net.anatolich.stitcher.color;

import lombok.Value;

import java.awt.*;

/**
 * Color occurence stats entry.
 */
@Value
public class HistogramEntry {
    private final Color color;
    private final int pixelCount;
    private final float percentage;
}
