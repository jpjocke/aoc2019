package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCode;

import java.util.List;

public class OpOutput extends Op {
    private Argument arg;
    private List<Long> output;

    public OpOutput(Argument arg, List<Long> output) {
        this.arg = arg;
        this.output = output;
    }

    @Override
    public int execute(int currentOp, List<IntCode> operations, IntCode relativeBase) {
        long val = arg.getRealValue(operations, relativeBase.getValue());
        output.add(val);
        output.stream().forEach(o -> System.out.println("    o: " + o));
        return currentOp + 2;
    }


    @Override
    public String toString() {
        return "OpOutput{" +
                "arg=" + arg +
                ", output=" + output +
                '}';
    }
}
