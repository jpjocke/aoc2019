package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;

public class OpInput extends Op {
    // this arg is always reference ????
    private Argument arg;
    private long input;

    public OpInput(Argument arg, long input) {
        this.arg = arg;
        this.input = input;
    }

    @Override
    public int execute(int currentOp, IntCodes intCodes) {
        long index = arg.getValue();
        if (arg.getMode() == Argument.Mode.RELATIVE) {
            index = arg.getValue() + intCodes.getRelativeBase();
        }
        System.out.println("  -> Input value is: " + input + ", stored at index: " + index);
        intCodes.setValueAtIndex(index, input);
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
