package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;

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
    public int execute(int currentOp, IntCodes intCodes) {
        long val1 = arg1.getRealValue(intCodes);
        long val2 = arg2.getRealValue(intCodes);

        long index = result.getValue();
        if (result.getMode() == Argument.Mode.RELATIVE) {
            index = result.getValue() + intCodes.getRelativeBase();
        }

        if (val1 == val2) {
            intCodes.setValueAtIndex(index, 1);
        } else {
            intCodes.setValueAtIndex(index, 0);
        }
        return currentOp + 4;
    }

    @Override
    public String toString() {
        return "OpEquals{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", result=" + result +
                '}';
    }
}
