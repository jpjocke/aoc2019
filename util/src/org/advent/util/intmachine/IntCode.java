package org.advent.util.intmachine;

public class IntCode {
    private long value;

    public IntCode(long value) {
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
