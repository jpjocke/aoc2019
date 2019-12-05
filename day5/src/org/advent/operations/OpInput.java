package org.advent.operations;

import org.advent.IntCode;

import java.util.List;

public class OpInput extends Op {
   // private Argument arg;
    private int value;

    public OpInput(int value) {
       // this.arg = new Argument(value, Argument.Mode.ACTUAL);
        this.value = value;
    }

    @Override
    public int execute(List<IntCode> operations) {
        operations.get(value).setValue(value);
        return 2;
    }
}
