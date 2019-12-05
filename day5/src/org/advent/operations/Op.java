package org.advent.operations;

import org.advent.IntCode;

import java.util.List;

public abstract class Op {
    public boolean isExit() {
        return false;
    }

    /**
     * Return amount of arguments used in list
     * @param operations
     * @return
     */
    public abstract int execute(List<IntCode> operations);
}
