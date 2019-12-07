package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpOutput extends Op {
    private Argument arg;
    private List<Integer> output;

    public OpOutput(Argument arg, List<Integer> output) {
        this.arg = arg;
        this.output = output;
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations) {
        output.add(operations.get(arg.getValue()).getValue());
        return currentOp + 2;
    }
}
