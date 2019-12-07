package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpJumpIfTrue extends Op {
    private Argument arg1;
    private Argument arg2;

    public OpJumpIfTrue(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations) {
        if (arg1.getRealValue(operations) != 0) {
            return arg2.getRealValue(operations);
        }
        return currentOp + 3;
    }
}
