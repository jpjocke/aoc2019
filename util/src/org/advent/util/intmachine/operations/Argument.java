package org.advent.util.intmachine.operations;

import org.advent.util.intmachine.IntCodes;

public class Argument {
    long value;

    Mode mode;

    public Argument(long value, int mode) {
        this.value = value;
        switch (mode) {
            case 2:
                this.mode = Mode.RELATIVE;
                break;
            case 1:
                this.mode = Mode.IMMEDIATE;
                break;
            case 0:
            default:
                this.mode = Mode.POSITION;
        }
    }

    public long getValue() {
        return value;
    }

    public long getRealValue(IntCodes intCodes) {
        switch (mode) {
            case POSITION:
                return intCodes.get((int) value);
            case RELATIVE:
                return intCodes.get((int) (value + intCodes.getRelativeBase()));
            case IMMEDIATE:
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

    public enum Mode {IMMEDIATE, POSITION, RELATIVE}
}
