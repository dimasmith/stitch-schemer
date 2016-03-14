package net.anatolich.stitcher.color;


import java.awt.Color;
import java.util.*;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

/**
 * Contain all colors in image.
 */
public class ColorData {

    private final Map<Color, Integer> histogram;
    private final Set<Color> distinctColors;

    private ColorData(Map<Color, Integer> histogram) {
        this.histogram = unmodifiableMap(histogram);
        this.distinctColors = unmodifiableSet(histogram.keySet());
    }

    public static Builder buildColorData() {
        return new Builder();
    }

    public Set<Color> imageColors() {
        return distinctColors;
    }

    public Map<Color, Integer> histogram() {
        return histogram;
    }

    public static class Builder {

        private Map<Color, Integer> histogram = new HashMap<>();

        public Builder withColor(Color color) {
            histogram.putIfAbsent(color, 0);
            histogram.compute(color, (c, count) -> count + 1);
            return this;
        }

        public ColorData build() {
            return new ColorData(histogram);
        }
    }
}
