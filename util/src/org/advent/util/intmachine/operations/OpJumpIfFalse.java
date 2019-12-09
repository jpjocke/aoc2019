package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpJumpIfFalse extends Op {
    private Argument arg1;
    private Argument arg2;

    public OpJumpIfFalse(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations, IntCode relativeBase) {
        if (arg1.getRealValue(operations, relativeBase.getValue()) == 0) {
            return (int)arg2.getRealValue(operations, relativeBase.getValue());
        }
        return currentOp + 3;
    }

    @Override
    public String toString() {
        return "OpJumpIfFalse{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }
}
