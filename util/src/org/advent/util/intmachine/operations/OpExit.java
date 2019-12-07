package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpExit extends Op {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations) {
        return currentOp + 1;
    }

    @Override
    public String toString() {
        return "OpExit{}";
    }
}
