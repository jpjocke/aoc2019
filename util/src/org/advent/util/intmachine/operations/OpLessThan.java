package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpLessThan extends Op {
    private Argument arg1;
    private Argument arg2;
    private Argument result;

    public OpLessThan(List<Argument> arguments) {
        this.arg1 = arguments.get(0);
        this.arg2 = arguments.get(1);
        this.result = arguments.get(2);
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations, IntCode relativeBase) {
        long val1 = arg1.getRealValue(operations, relativeBase.getValue());
        long val2 = arg2.getRealValue(operations, relativeBase.getValue());

        long index = result.getValue();
        if (result.getMode() == Argument.Mode.RELATIVE) {
            index = result.getValue() + relativeBase.getValue();
        }

        if (val1 < val2) {
            operations.get((int)index).setValue(1);
        } else {
            operations.get((int)index).setValue(0);
        }
        return currentOp + 4;
    }

    @Override
    public String toString() {
        return "OpLessThan{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", result=" + result +
                '}';
    }
}
