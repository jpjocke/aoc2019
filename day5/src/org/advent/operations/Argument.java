package org.advent.operations;

import org.advent.IntCode;

import java.util.List;

public class Argument {
    public enum Mode{ACTUAL, REFERENCE};
    int value;
    Mode mode;

    public Argument(int value, Mode mode) {
        this.value = value;
        this.mode = mode;
    }

    public int getValue(List<IntCode> intCodes) {
        switch (mode) {
            case REFERENCE:
                return intCodes.get(value).getValue();
            case ACTUAL:
            default:
                return value;
        }
    }
}
