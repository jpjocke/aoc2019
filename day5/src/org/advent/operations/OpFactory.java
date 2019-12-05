package org.advent.operations;

import org.advent.IntCode;

import java.util.ArrayList;
import java.util.List;

public class OpFactory {
    public static Op buildOp(int index, List<IntCode> codes, List<Integer> output) {
        int[] digits = OpFactory.toInts(codes.get(index).getValue());
        int opCode = getOpCode(digits);
        if (opCode == 99) {
            // Exit OP - no params
            return new OpExit();
        }
        if (opCode == 3) {
            return new OpInput(codes.get(index + 1).getValue());
        }
        if (opCode == 4) {
            return new OpOutput(codes.get(index + 1).getValue(), output);
        }

        List<Argument> parameters = new ArrayList<>();
        // max 3 parameters
        for (int i = 0; i < 2; i++) {
            int digitIndex = 2 + i + 1;
            if (digits.length >= digitIndex) {
                parameters.add(new Argument(codes.get(index + i + 1).getValue(), digits[digits.length - digitIndex] == 1 ? Argument.Mode.ACTUAL : Argument.Mode.REFERENCE));
            } else {
                parameters.add(new Argument(codes.get(index + i + 1).getValue(), Argument.Mode.REFERENCE));
            }
        }
        // result is always ACTUAL
        parameters.add(new Argument(codes.get(index + 3).getValue(), Argument.Mode.ACTUAL));

        if (opCode == 1) {
            return new OpAdd(parameters);
        } else {
            return new OpMultiply(parameters);
        }
    }

    private static int getOpCode(int[] digits) {
        int a = digits.length >= 2 ? digits[digits.length - 2] * 10: 0;
        int opCode = a + digits[digits.length - 1];
        return opCode;
    }

    private static int[] toInts(int larger) {
        String number = String.valueOf(larger);
        char[] digitsChar = number.toCharArray();
        int[] digits = new int[digitsChar.length];
        for(int i = 0; i < digitsChar.length; i++) {
            digits[i] = Character.digit(digitsChar[i], 10);
        }
        return digits;
    }
}
