package org.advent.util;


import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static int[] toDigits(long larger) {
        return toDigits(larger, String.valueOf(larger).length());
    }

    public static int[] toDigits(long larger, int length) {
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

    public static List<Long> toOperations(String string) {
        String[] split = string.split(",");
        List<Long> ops =  Arrays.stream(split)
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());
        return ops;
    }

    public static long runMachineWithSingleOutput(IntCodes ops, int input) {
        return runMachineWithSingleOutput(ops, new int[]{input});
    }

    public static long runMachineWithSingleOutput(IntCodes ops, int[] input) {
        IntMachine im = new IntMachine(ops);
        for (int i = 0; i < input.length; i++) {
            im.addInput(input[i]);
        }
        im.execute();
        List<Long> output = im.getOutput();
        output.stream().forEach(number -> System.out.println("Output: " + number));
        return im.getLastOutput();
    }
}
