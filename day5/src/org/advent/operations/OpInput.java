package org.advent.operations;

import org.advent.IntCode;

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
        operations.get(arg.getValue()).setValue(input);
        return currentOp + 2;
    }
}
