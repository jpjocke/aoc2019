package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.Output;

import java.util.List;

public class OpOutput extends Op {
    private Argument arg;
    private Output output;

    public OpOutput(Argument arg, Output output) {
        this.arg = arg;
        this.output = output;
    }

    @Override
    public int execute(int currentOp, IntCodes intCodes) {
        long val = arg.getRealValue(intCodes);
        output.add(val);
     //   output.stream().forEach(o -> System.out.println("    o: " + o));
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
