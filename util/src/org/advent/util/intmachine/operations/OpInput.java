package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpInput extends Op {
    // this arg is always reference ????
    private Argument arg;
    private int input;

    public OpInput(Argument arg, int input) {
        this.arg = arg;
        this.input = input;
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations, IntCode relativeBase) {
        long val = arg.getValue();
        if (arg.getMode() == Argument.Mode.RELATIVE) {
            val = arg.getRealValue(operations, relativeBase.getValue());
        }
        System.out.println("  -> Input value is: " + input + ", stored at index: " + val);
        operations.get((int)val).setValue(input);
        return currentOp + 2;
    }

    @Override
    public String toString() {
        return "OpInput{" +
                "arg=" + arg +
                ", input=" + input +
                '}';
    }
}
