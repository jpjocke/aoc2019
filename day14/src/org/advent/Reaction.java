package org.advent;

import java.util.List;

public class Reaction {
    private List<Material> input;
    private Material output;

    public Reaction(List<Material> input, Material output) {
        this.input = input;
        this.output = output;
    }

    public List<Material> getInput() {
        return input;
    }

    public Material getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                input +
                " => " + output +
                '}';
    }
}
