package org.advent.util.intmachine.operations;

import org.advent.util.Util;
import org.advent.util.intmachine.Input;
import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.NoMoreInputException;

import java.util.ArrayList;
import java.util.List;

public class OpFactory {
    private IntCodes intCodes;
    private List<Long> output;
    private Input input;

    public OpFactory(IntCodes intCodes, List<Long> output, Input input) {
        setup(intCodes, output, input);
    }

    public OpFactory(IntCodes intCodes, List<Long> output, int input) {
        Input i = new Input();
        i.addInput(input);
        setup(intCodes, output, i);
    }

    private void setup(IntCodes intCodes, List<Long> output, Input input) {
        this.intCodes = intCodes;
        this.output = output;
        this.input = input;
    }

    public Op buildOp(int index) throws NoMoreInputException {
        int[] digits = Util.toDigits(intCodes.get(index), 5);
        int opCode = getOpCode(digits);
        if (opCode == 99) {
            return new OpExit();
        }

        int arguments = getArgumentsToUse(opCode);

        List<Argument> parameters = new ArrayList<>();
        for (int i = 0; i < arguments; i++) {
            int digitIndex = 2 + i + 1;
            parameters.add(new Argument(intCodes.get(index + i + 1), digits[digits.length - digitIndex]));
        }

        if (opCode == 3) {
            int inputToUse = input.getNextInput();
            return new OpInput(parameters.get(0), inputToUse);
        }
        if (opCode == 4) {
            return new OpOutput(parameters.get(0), output);
        } else if (opCode == 1) {
            return new OpAdd(parameters);
        } else if (opCode == 2) {
            return new OpMultiply(parameters);
        } else if (opCode == 5) {
            return new OpJumpIfTrue(parameters);
        } else if (opCode == 6) {
            return new OpJumpIfFalse(parameters);
        } else if (opCode == 7) {
            return new OpLessThan(parameters);
        } else if (opCode == 8) {
            return new OpEquals(parameters);
        } else if (opCode == 9) {
            return new OpRelativeBaseChange(parameters.get(0));
        }
        return new OpExit();
    }

    private int getOpCode(int[] digits) {
        int a = digits[digits.length - 2] * 10;
        int opCode = a + digits[digits.length - 1];
        return opCode;
    }

    private int getArgumentsToUse(int opCode) {
        if (opCode == 3 || opCode == 4 || opCode == 9) {
            return 1;
        }
        if (opCode == 5 ||
                opCode == 6) {
            return 2;
        }
        if (opCode == 1 ||
                opCode == 2 ||
                opCode == 7 ||
                opCode == 8) {
            return 3;
        }
        return 0;
    }
}
