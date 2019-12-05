package org.advent.operations;

import org.advent.IntCode;

import java.util.List;

public class OpOutput extends Op {
    private int value;
    private List<Integer> output;

    public OpOutput(int value, List<Integer> output) {
        this.value = value;
        this.output = output;
    }

    @Override
    public int execute(List<IntCode> operations) {
        output.add(operations.get(value).getValue());
        return 2;
    }
}
