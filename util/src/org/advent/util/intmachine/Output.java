package org.advent.util.intmachine;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private List<Long> output;

    public Output() {
        output = new ArrayList<>();
    }

    public synchronized void add(long output) {
        this.output.add(output);
    }

    public synchronized int size() {
        return output.size();
    }

    public synchronized long getOutputAt(int index) {
        return output.get(index);
    }

    public synchronized void clear() {
        output.clear();
    }

    public List<Long> getFullOutput() {
        return output;
    }
}
