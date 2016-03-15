package net.anatolich.stitcher.color;


import java.awt.Color;
import java.util.*;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

/**
 * Contain all colors in image.
 */
public class ColorHistogram {

    private final Map<Color, Integer> histogram;
    private final Set<Color> distinctColors;

    private ColorHistogram(Map<Color, Integer> histogram) {
        this.histogram = unmodifiableMap(histogram);
        this.distinctColors = unmodifiableSet(histogram.keySet());
    }

    public static Builder buildColorData() {
        return new Builder();
    }

    public static ColorHistogram of(Color... colors) {
        return buildColorData().withColors(colors).build();
    }

    public static ColorHistogram of(Iterable<Color> colors) {
        final Builder colorDataBuilder = buildColorData();
        for (Color color : colors) {
            colorDataBuilder.withColors(color);
        }
        return colorDataBuilder.build();
    }

    public Set<Color> imageColors() {
        return distinctColors;
    }

    public Map<Color, Integer> histogram() {
        return histogram;
    }

    public int countColorPixels(Color color) {
        return histogram.getOrDefault(color, 0);
    }

    public static class Builder {

        private Map<Color, Integer> histogram = new HashMap<>();

        public Builder withColors(Color... colors) {
            for (Color color : colors) {
                addColor(color);
            }
            return this;
        }

        private void addColor(Color color) {
            histogram.putIfAbsent(color, 0);
            histogram.compute(color, (c, count) -> count + 1);
        }

        public ColorHistogram build() {
            return new ColorHistogram(histogram);
        }
    }
}
