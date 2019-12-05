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
    public int execute(List<IntCode> operations) {
        int val1  = arg1.getValue(operations);
        int val2 = arg2.getValue(operations);
        int total = val1 * val2;
        operations.get(result.getValue(operations)).setValue(total);
        return 4;
    }
}
