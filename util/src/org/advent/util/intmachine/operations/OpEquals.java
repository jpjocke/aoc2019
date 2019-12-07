package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpEquals extends Op {
    private Argument arg1;
    private Argument arg2;
    private Argument result;

    public OpEquals(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
        this.result = arguments.get(2);
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations) {
        int val1 = arg1.getRealValue(operations);
        int val2 = arg2.getRealValue(operations);
        if (val1 == val2) {
            operations.get(result.getValue()).setValue(1);
        } else {
            operations.get(result.getValue()).setValue(0);
        }
        return currentOp + 4;
    }
}
