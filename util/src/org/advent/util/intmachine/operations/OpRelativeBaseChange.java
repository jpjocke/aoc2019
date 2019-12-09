package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;

public class OpRelativeBaseChange extends Op {
    private Argument arg;

    public OpRelativeBaseChange(Argument arg) {
        this.arg = arg;
    }

    @Override
    public int execute(int currentOp, IntCodes intCodes) {
        long now = intCodes.getRelativeBase();
        long result = now + arg.getRealValue(intCodes);
        intCodes.setRelativeBase((int) result);
        return currentOp + 2;
    }


    @Override
    public String toString() {
        return "OpRelativeBaseChange{" +
                "arg=" + arg +
                '}';
    }
}
