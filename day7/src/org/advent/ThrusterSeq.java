package org.advent;

import org.advent.util.Util;
import org.advent.util.intmachine.IntCode;
import org.advent.util.intmachine.IntMachine;

import java.util.List;

public class ThrusterSeq {
    private int[] sequence;
    private String input;

    public ThrusterSeq(int[] sequence, String input) {
        this.sequence = sequence;
        this.input = input;
    }

    public int runThrusters() {
        int thrusterInputOutput = 0;
        for (int i = 0; i < sequence.length; i++) {
            List<IntCode> ops = Util.toOperations(input);
            int[] input = new int[]{sequence[i], sequence[i]};
            int out = runMachineWithSingleOutput(ops, input);
            input[1] = thrusterInputOutput;
            thrusterInputOutput = runMachineWithSingleOutput(ops, input);
            System.out.println("#### Output: " + thrusterInputOutput + " for index: " + i);
        }
        return thrusterInputOutput;
    }

    private int runMachineWithSingleOutput(List<IntCode> ops, int[] input) {
        IntMachine im = new IntMachine(ops, input);
        im.execute();
        List<Integer> output = im.getOutput();
        output.stream().forEach(number -> System.out.println("Output: " + number));
        return output.get(output.size() - 1);
    }
}
