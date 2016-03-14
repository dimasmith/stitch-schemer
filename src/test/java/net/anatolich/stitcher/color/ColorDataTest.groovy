package net.anatolich.stitcher.color

import spock.lang.Specification

import java.awt.Color

class ColorDataTest extends Specification {

    def 'empty color data has empty list of image colors'() {
        when:
        ColorData emptyColorData = ColorData.buildColorData().build()

        then:
        emptyColorData.imageColors().isEmpty()
    }

    def 'color data contains all distinct colors'() {
        given:
        ColorData colorData = ColorData.buildColorData()
            .withColor(Color.BLACK)
            .withColor(Color.RED)
            .withColor(Color.BLACK)
            .build()

        when:
        Set<Color> imageColors = colorData.imageColors()

        then:
        imageColors.size() == 2
        imageColors.contains(Color.BLACK)
        imageColors.contains(Color.RED)
    }

    def 'count color entries'() {
        given:
        ColorData colorData = ColorData.buildColorData()
                .withColor(Color.BLACK)
                .withColor(Color.RED)
                .withColor(Color.BLACK)
                .build()

        when:
        Map<Color, Integer> histogram = colorData.histogram()

        then:
        histogram.size() == 2
        histogram.get(Color.BLACK) == 2
        histogram.get(Color.RED) == 1
    }
}
