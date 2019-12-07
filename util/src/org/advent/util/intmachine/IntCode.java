package org.advent.util.intmachine;

public class IntCode {
    private int value;

    public IntCode(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
