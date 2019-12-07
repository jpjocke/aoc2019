package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpInput extends Op {
    private Argument arg;
    private int input;

    public OpInput(Argument arg, int input) {
        this.arg = arg;
        this.input = input;
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations) {
        System.out.println("  -> Input value is: " + input + ", stored at index: " + arg.getValue());
        operations.get(arg.getValue()).setValue(input);
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
