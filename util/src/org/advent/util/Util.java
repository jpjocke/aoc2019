package org.advent.util;


import org.advent.util.intmachine.IntCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static int[] toDigits(int larger) {
        return toDigits(larger, String.valueOf(larger).length());
    }

    public static int[] toDigits(int larger, int length) {
        String number = String.valueOf(larger);
        char[] digitsChar = number.toCharArray();
        int[] digits = new int[digitsChar.length];
        for (int i = 0; i < digitsChar.length; i++) {
            digits[i] = Character.digit(digitsChar[i], 10);
        }

        if (digits.length != length) {
            int[] filledDigits = new int[length];
            for (int i = 0; i < filledDigits.length; i++) {
                int digitIndex = digits.length - 1 - i;
                int filledIndex = filledDigits.length - 1 - i;
                if (digitIndex >= 0) {
                    filledDigits[filledIndex] = digits[digitIndex];
                } else {
                    filledDigits[filledIndex] = 0;
                }
            }
            digits = filledDigits;
        }

        return digits;
    }

    public static List<IntCode> toOperations(String string) {
        String[] split = string.split(",");
        return Arrays.stream(split)
                .map(s -> new IntCode(Integer.parseInt(s)))
                .collect(Collectors.toList());
    }
}
