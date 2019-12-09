package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpRelativeBaseChange extends Op {
    private Argument arg;

    public OpRelativeBaseChange(Argument arg) {
        this.arg = arg;
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations, IntCode relativeBase) {
        long now = relativeBase.getValue();
        long result = now + arg.getRealValue(operations, relativeBase.getValue());
        relativeBase.setValue(result);
        return currentOp + 2;
    }


    @Override
    public String toString() {
        return "OpRelativeBaseChange{" +
                "arg=" + arg +
                '}';
    }
}
