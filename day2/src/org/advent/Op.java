package org.advent;

import java.util.List;

public class Op {
    private int value;

    public Op(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void executeOp(List<Op> operations, int currentPosition) {
        Op one = operations.get(currentPosition + 1);
        Op two = operations.get(currentPosition + 2);
        Op resultOp = operations.get(currentPosition + 3);

        int val1 = operations.get(one.getValue()).getValue();
        int val2 = operations.get(two.getValue()).getValue();
        int value = 0;
        if (isAdd()) {
          //  System.out.println("Adding " + val1 + " + " + val2);
            value = val1 + val2;

        } else if(isMultiply()) {
           // System.out.println("Multiplying " + val1 + " + " + val2);
            value = val1 * val2;
        } else {
            throw new RuntimeException("Something is wrong: op: " + value);
        }
        operations.get(resultOp.getValue()).setValue(value);
    }

    public boolean isExit() {
        return value == 99;
    }

    public boolean isAdd() {
        return value == 1;
    }


    public boolean isMultiply() {
        return value == 2;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
