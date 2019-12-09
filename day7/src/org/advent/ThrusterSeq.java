package org.advent;

import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

public class ThrusterSeq {
    String input;
    private int[] sequence;
    private ThrusterInfo[] thrusters;

    public ThrusterSeq(int[] sequence, String input) {
        this.input = input;
        this.sequence = sequence;
        thrusters = new ThrusterInfo[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            IntCodes ops = new IntCodes(input);
            thrusters[i] = new ThrusterInfo(ops, sequence[i]);
        }
    }

    public long runThrusters() {
        long thrusterInputOutput = 0;

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
        long lastOutput;

        public ThrusterInfo(IntCodes ops, int phase) {
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

        public void run(long input) {
            System.out.println("#### Run phase: " + phase + " input: " + input);
            machine.addInput((int) input);

            machine.execute();
            lastOutput = machine.getLastOutput();
        }
    }
}
