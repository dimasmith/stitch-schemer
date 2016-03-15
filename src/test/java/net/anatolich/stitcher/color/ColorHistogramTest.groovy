package net.anatolich.stitcher.color

import spock.lang.Specification

import java.awt.Color

class ColorHistogramTest extends Specification {

    ColorHistogram emptyColorData = ColorHistogram.of([])

    def 'empty color data has empty list of image colors'() {
        expect:
        emptyColorData.imageColors().isEmpty()
    }

    def 'color data contains all distinct colors'() {
        given:
        def colorData = ColorHistogram.of(Color.BLACK, Color.RED, Color.BLACK)

        when:
        Set<Color> imageColors = colorData.imageColors()

        then:
        imageColors.size() == 2
        imageColors.contains(Color.BLACK)
        imageColors.contains(Color.RED)
    }

    def 'count color entries'() {
        given:
        def colorData = ColorHistogram.of(Color.BLACK, Color.RED, Color.BLACK)

        when:
        Map<Color, Integer> histogram = colorData.histogram()

        then:
        histogram.size() == 2
        histogram.get(Color.BLACK) == 2
        histogram.get(Color.RED) == 1
    }

    def 'pixel count for color not in image is 0'() {
        expect:
        emptyColorData.countColorPixels(Color.BLACK) == 0
    }

    def 'count pixels of particular color'() {
        when:
        def colorData = ColorHistogram.of(Color.BLACK)

        then:
        colorData.countColorPixels(Color.BLACK) == 1
    }

    def 'create color data using list of colors'() {
        given: 'list of colors'
        List<Color> colors = [Color.BLACK, Color.BLACK, Color.RED, Color.GREEN]

        when: 'create color data of list'
        def colorData = ColorHistogram.of(colors)

        then:
        colorData.imageColors().size() == 3
        colorData.imageColors().contains(Color.BLACK)
        colorData.imageColors().contains(Color.RED)
        colorData.imageColors().contains(Color.GREEN)
    }
}
