package org.advent.util.intmachine.operations;


import org.advent.util.intmachine.IntCode;

import java.util.List;

public class Argument {
    public enum Mode{ACTUAL, REFERENCE, RELATIVE};
    long value;
    Mode mode;

    public Argument(long value, int mode) {
        this.value = value;
        switch (mode) {
            case 2:
                this.mode = Mode.RELATIVE;
                break;
            case 1:
                this.mode = Mode.ACTUAL;
                break;
            case 0:
            default:
                this.mode = Mode.REFERENCE;
        }
    }

    public long getValue() {
        return value;
    }

    public long getRealValue(List<IntCode> intCodes, long relativeBase) {
        switch (mode) {
            case REFERENCE:
                return intCodes.get((int)value).getValue();
            case RELATIVE:
                return intCodes.get((int)(value + relativeBase)).getValue();
            case ACTUAL:
            default:
                return value;
        }
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "Argument{" +
                "value=" + value +
                ", mode=" + mode +
                '}';
    }
}
