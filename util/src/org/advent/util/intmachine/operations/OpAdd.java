package org.advent.util.intmachine.operations;


import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpAdd extends Op {
    private Argument arg1;
    private Argument arg2;
    // result is always ref
    private Argument result;

    public OpAdd(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
        this.result = arguments.get(2);
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations, IntCode relativeBase) {
        long val1  = arg1.getRealValue(operations, relativeBase.getValue());
        long val2 = arg2.getRealValue(operations, relativeBase.getValue());
        long total = val1 + val2;

        // result is always real
        operations.get((int)result.getValue()).setValue(total);
        return currentOp + 4;
    }

    @Override
    public String toString() {
        return "OpAdd{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", result=" + result +
                '}';
    }
}
