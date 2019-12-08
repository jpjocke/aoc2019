package org.advent.util.intmachine;

import java.util.ArrayList;
import java.util.List;

public class Input {
    int currentInput;
    private List<Integer> input;

    public Input() {
        this.input = new ArrayList<>();
        this.currentInput = 0;
    }

    public void addInput(int next) {
        input.add(next);
    }

    public void resetCounter() {
        currentInput = 0;
    }

    public int getNextInput() throws NoMoreInputException {
        if (currentInput >= input.size()) {
            throw new NoMoreInputException();
        }
        int next = input.get(currentInput);
        currentInput++;
        return next;
    }
}
