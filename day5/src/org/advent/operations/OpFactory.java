package org.advent.operations;

import org.advent.IntCode;

import java.util.ArrayList;
import java.util.List;

public class OpFactory {
    private List<IntCode> codes;
    private List<Integer> output;
    private int input;

    public OpFactory(List<IntCode> codes, List<Integer> output, int input) {
        this.codes = codes;
        this.output = output;
        this.input = input;
    }

    public Op buildOp(int index) {
        int[] digits = toInts(codes.get(index).getValue());
        int opCode = getOpCode(digits);
        if (opCode == 99) {
            return new OpExit();
        }

        int arguments = getArgumentsToUse(opCode);

        List<Argument> parameters = new ArrayList<>();
        for (int i = 0; i < arguments; i++) {
            int digitIndex = 2 + i + 1;
            if (digits.length >= digitIndex) {
                parameters.add(new Argument(codes.get(index + i + 1).getValue(), digits[digits.length - digitIndex] == 1 ? Argument.Mode.ACTUAL : Argument.Mode.REFERENCE));
            } else {
                parameters.add(new Argument(codes.get(index + i + 1).getValue(), Argument.Mode.REFERENCE));
            }
        }
        // result is always ACTUAL
      //  parameters.add(new Argument(codes.get(index + arguments).getValue(), Argument.Mode.ACTUAL));

        if (opCode == 3) {
            return new OpInput(parameters.get(0), input);
        }
        if (opCode == 4) {
            return new OpOutput(parameters.get(0), output);
        } else if (opCode == 1) {
            return new OpAdd(parameters);
        } else if (opCode == 2 || opCode == 0) { // 0???? seems to work but is probably a bug somewhere
            return new OpMultiply(parameters);
        } else if (opCode == 5) {
            return new OpJumpIfTrue(parameters);
        } else if (opCode == 6) {
            return new OpJumpIfFalse(parameters);
        } else if (opCode == 7) {
            return new OpLessThan(parameters);
        } else if (opCode == 8) {
            return new OpEquals(parameters);
        }
        return new OpExit();
    }

    private int getOpCode(int[] digits) {
        int a = digits.length >= 2 ? digits[digits.length - 2] * 10 : 0;
        int opCode = a + digits[digits.length - 1];
        return opCode;
    }

    private int[] toInts(int larger) {
        String number = String.valueOf(larger);
        char[] digitsChar = number.toCharArray();
        int[] digits = new int[digitsChar.length];
        for (int i = 0; i < digitsChar.length; i++) {
            digits[i] = Character.digit(digitsChar[i], 10);
        }
        return digits;
    }

    private int getArgumentsToUse(int opCode) {
        if (opCode == 3 || opCode == 4) {
            return 1;
        }
        if (opCode == 5 ||
            opCode == 6) {
            return 2;
        }
        if (opCode == 1 ||
            opCode == 2 || opCode == 0 || //0???
            opCode == 7 ||
            opCode == 8) {
            return 3;
        }
        return 0;
    }
}
