package org.advent;

import java.util.Arrays;

public class Layer {
    private int[] pixels;

    public Layer(char[] pixels) {
        this.pixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = Character.digit(pixels[i], 10);
        }
    }

    public int countDigits(int digit) {
        int counter = 0;
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] == digit) {
                counter++;
            }
        }
        return counter;
    }

    public int getDigitAt(int at) {
        return pixels[at];
    }

    public int length() {
        return pixels.length;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "pixels=" + Arrays.toString(pixels) +
                '}';
    }
}
