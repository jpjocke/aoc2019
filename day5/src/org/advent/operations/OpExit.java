package org.advent.operations;

import org.advent.IntCode;

import java.util.List;

public class OpExit extends Op {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public int execute(List<IntCode> operations) {
        return 1;
    }
}
