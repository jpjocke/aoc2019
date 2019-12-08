package org.advent;

import java.util.ArrayList;
import java.util.List;

public class PixelParser {
    public static Picture parse(String s, int width, int height) {

        int pointer = 0;
        char[] raw = s.toCharArray();

        List<List<Layer>> picture = new ArrayList<>();

        List<Layer> currentRow = new ArrayList<>();
        while (pointer < raw.length) {
            Layer p = toPixel(raw, pointer, width);
            currentRow.add(p);
            pointer += width;
            if (currentRow.size() == height) {
                picture.add(currentRow);
                currentRow = new ArrayList<>();

            }
        }

        return new Picture(picture);
    }

    private static Layer toPixel(char[] digitsChar, int pointer, int length) {
        char[] pixels = new char[length];
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = digitsChar[pointer + i];
        }
        return new Layer(pixels);
    }

}
