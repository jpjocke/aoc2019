package org.advent;

import org.advent.util.Util;
import org.advent.util.intmachine.IntCode;
import org.advent.util.intmachine.IntMachine;

import java.util.List;

public class ThrusterSeq {
    String input;
    private int[] sequence;
    private ThrusterInfo[] thrusters;

    public ThrusterSeq(int[] sequence, String input) {
        this.input = input;
        this.sequence = sequence;
        thrusters = new ThrusterInfo[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            List<IntCode> ops = Util.toOperations(input);
            thrusters[i] = new ThrusterInfo(ops, sequence[i]);
        }
    }

    public int runThrusters() {
        int thrusterInputOutput = 0;

        boolean isDone = false;
        int index = 0;
        while (!isDone) {

            ThrusterInfo thrusterInfo = thrusters[index];
            thrusterInfo.run(thrusterInputOutput);

            thrusterInputOutput = thrusterInfo.lastOutput;

            if (index == sequence.length - 1) {
                // only exit if last is done
                isDone = thrusterInfo.isDone();
            }

            index++;
            if (index >= sequence.length) {
                index = 0;
            }
            System.out.println("#### Output: " + thrusterInputOutput + " for index: " + index);
        }

        return thrusterInputOutput;
    }

    private class ThrusterInfo {
        int phase;
        IntMachine machine;
        int lastOutput;

        public ThrusterInfo(List<IntCode> ops, int phase) {
            this.phase = phase;
            machine = new IntMachine(ops);
            // behövs 2?
            machine.addInput(phase);
            amplify();
            lastOutput = 0;
        }

        private void amplify() {
            System.out.println("#### Amplify phase: " + phase);
            machine.execute();
        }

        public boolean isDone() {
            return machine.isDone();
        }

        public void run(int input) {
            System.out.println("#### Run phase: " + phase + " input: " + input);
            machine.addInput(input);

            machine.execute();
            lastOutput = machine.getLastOutput();
        }
    }
}
