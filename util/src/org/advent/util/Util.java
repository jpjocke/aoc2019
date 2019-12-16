package org.advent.util;


import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public static int[] toDigits(long number, int length) {
        String numberS = String.valueOf(number);
        while (numberS.length() != length) {
            numberS = "0" + numberS;
        }
        return toDigits(numberS);
    }

    public static int[] toDigits(long number) {
        return toDigits(number, String.valueOf(number).length());
    }

    public static int[] toDigits(String number) {
        char[] digitsChar = number.toCharArray();
        int[] digits = new int[digitsChar.length];
        for (int i = 0; i < digitsChar.length; i++) {
            digits[i] = Character.digit(digitsChar[i], 10);
        }
        return digits;
    }

    public static byte[] toDigitsByte(long number) {
        return toDigitsByte(String.valueOf(number));
    }

    public static byte[] toDigitsByte(long number, int length) {
        String numberS = String.valueOf(number);
        while (numberS.length() != length) {
            numberS = "0" + numberS;
        }
        return toDigitsByte(numberS);
    }

    public static byte[] toDigitsByte(String number) {
        char[] digitsChar = number.toCharArray();
        byte[] digits = new byte[digitsChar.length];
        for (int i = 0; i < digitsChar.length; i++) {
            digits[i] = (byte)Character.digit(digitsChar[i], 10);
        }
        return digits;
    }

    public static List<Long> toOperations(String string) {
        String[] split = string.split(",");
        List<Long> ops = Arrays.stream(split)
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

    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    public static Map<Integer, Integer> getPrimeFactors(long number) {
        long absNumber = Math.abs(number);

        Map<Integer, Integer> primeFactorsMap = new HashMap<Integer, Integer>();

        for (int factor = 2; factor <= absNumber; factor++) {
            while (absNumber % factor == 0) {
                Integer power = primeFactorsMap.get(factor);
                if (power == null) {
                    power = 0;
                }
                primeFactorsMap.put(factor, power + 1);
                absNumber /= factor;
            }
        }

        return primeFactorsMap;
    }

    public static BigInteger lcm2(long number1, long number2) {
        return lcm3(BigInteger.valueOf(number1), BigInteger.valueOf(number2));
    }

    public static BigInteger lcm3(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }
}
