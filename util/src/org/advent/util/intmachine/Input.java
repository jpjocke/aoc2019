package org.advent.util.intmachine;

import java.util.ArrayList;
import java.util.List;

public class Input {
    int currentInput;
    private List<Long> input;

    public Input() {
        this.input = new ArrayList<>();
        this.currentInput = 0;
    }

    public void addInput(long next) {
        input.add(next);
    }

    public void resetCounter() {
        currentInput = 0;
    }

    public long getNextInput() throws NoMoreInputException {
        if (currentInput >= input.size()) {
            throw new NoMoreInputException();
        }
        long next = input.get(currentInput);
        currentInput++;
        return next;
    }
}
