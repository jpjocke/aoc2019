package org.advent.util.intmachine.operations;


import org.advent.util.intmachine.IntCode;

import java.util.List;

public abstract class Op {
    public boolean isExit() {
        return false;
    }

    /**
     * Return next op to run
     * @param operations
     * @return
     */
    public abstract int execute(int currentOp, List<IntCode> operations, IntCode relativeBase);
}
