package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;

import java.util.List;

public class OpJumpIfTrue extends Op {
    private Argument arg1;
    private Argument arg2;

    public OpJumpIfTrue(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
    }

    @Override
    public int execute(int currentOp, IntCodes intCodes) {
        if (arg1.getRealValue(intCodes) != 0) {
            return (int) arg2.getRealValue(intCodes);
        }
        return currentOp + 3;
    }

    @Override
    public String toString() {
        return "OpJumpIfTrue{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }
}
