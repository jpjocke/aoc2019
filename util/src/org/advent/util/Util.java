package org.advent.util;


import org.advent.util.intmachine.IntCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static int[] toDigits(int larger) {
        String number = String.valueOf(larger);
        char[] digitsChar = number.toCharArray();
        int[] digits = new int[digitsChar.length];
        for (int i = 0; i < digitsChar.length; i++) {
            digits[i] = Character.digit(digitsChar[i], 10);
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
