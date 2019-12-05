package org.advent.operations;

import org.advent.IntCode;

import java.util.List;

public class OpMultiply extends Op {
    private Argument arg1;
    private Argument arg2;
    private Argument result;

    public OpMultiply(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
        this.result = arguments.get(2);
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations) {
        int val1  = arg1.getRealValue(operations);
        int val2 = arg2.getRealValue(operations);
        int total = val1 * val2;
        operations.get(result.getValue()).setValue(total);
        return currentOp + 4;
    }
}
