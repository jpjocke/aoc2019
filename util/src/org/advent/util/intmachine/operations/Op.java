package org.advent.util.intmachine.operations;


import org.advent.util.intmachine.IntCodes;

public abstract class Op {
    public boolean isExit() {
        return false;
    }

    public abstract int execute(int currentOp, IntCodes intCodes);
}
