package org.advent.util.intmachine.operations;

import org.advent.util.Util;
import org.advent.util.intmachine.IntCode;

import java.util.ArrayList;
import java.util.List;

public class OpFactory {
    private List<IntCode> codes;
    private List<Integer> output;
    private int[] input;
    private int inputIndex;

    public OpFactory(List<IntCode> codes, List<Integer> output, int[] input) {
        this.codes = codes;
        this.output = output;
        this.input = input;
        inputIndex = 0;
    }

    public OpFactory(List<IntCode> codes, List<Integer> output, int input) {
        this(codes, output, new int[]{input});
    }

    public void setInputAndReset(int[] input, List<Integer> output) {
        this.input = input;
        inputIndex = 0;
        this.output = output;
    }

    public Op buildOp(int index) {
        int[] digits = Util.toDigits(codes.get(index).getValue(), 4);
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
                // Result last arg is always ref
                parameters.add(new Argument(codes.get(index + i + 1).getValue(), Argument.Mode.REFERENCE));
            }
        }

        if (opCode == 3) {
            if(inputIndex >= input.length) {
                inputIndex = input.length -1;
            }
            int inputToUse = input[inputIndex];
            inputIndex++;
            return new OpInput(parameters.get(0), inputToUse);
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
        int a =  digits[digits.length - 2] * 10;
        int opCode = a + digits[digits.length - 1];
        return opCode;
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
