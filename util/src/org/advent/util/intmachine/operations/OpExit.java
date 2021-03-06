package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;

public class OpExit extends Op {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public int execute(int currentOp, IntCodes intCodes) {
        return currentOp + 1;
    }

    @Override
    public String toString() {
        return "OpExit{}";
    }
}
