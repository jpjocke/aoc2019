package org.advent;

import org.advent.util.intmachine.Output;

import java.util.ArrayList;
import java.util.List;

public class OutputCoordinator extends Thread {
    private List<Computer> computers;
    private List<Output> outputs;
    private List<Integer> outputPointers;
    private long NATx;
    private long NATy;
    private long NATyGiven;
    private boolean NATinit;

    public OutputCoordinator(int size) {
        outputs = new ArrayList<>();
        outputPointers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            outputPointers.add(0);
        }
        computers = new ArrayList<>();
        NATy = -1;
        NATyGiven = -1;
        NATinit = false;
    }

    /**
     * Must be in order!
     */
    public void addComputer(Computer c) {
        outputs.add(c.getOutput());
        computers.add(c);
    }

    @Override
    public void run() {
        boolean stop = false;
        for (int i = 0; i < computers.size(); i++) {
            computers.get(i).start();
        }

        boolean allIdle = false;
        while (!stop) {
            if (allIdle && NATinit) {
                if (NATy == NATyGiven) {
                    System.out.println("######## NAT SAME " + NATyGiven);
                    stop = true;
                }
                NATyGiven = NATy;
                System.out.println("++ All is idle sending NAT " + NATy + " to 0");
                computers.get(0).addInput(NATx, NATy);
            }

            for (int i = 0; i < outputPointers.size(); i++) {
                int pointer = outputPointers.get(i);
                Output o = outputs.get(i);
                if (o.size() >= pointer + 3) {
                    int to = (int) o.getOutputAt(pointer);
                    long x = o.getOutputAt(pointer + 1);
                    long y = o.getOutputAt(pointer + 2);
                    System.out.println("-- Output on index " + i + ", " + pointer + "/" + outputs.get(i).size());

                    System.out.println("-- To " + to + ", " + x + ", " + y);
                    if (to == 255) {
                        NATinit = true;
                        System.out.println("######## NAT " + y);
                            NATx = x;
                            NATy = y;
                    } else {
                        computers.get(to).addInput(x, y);
                    }
                    pointer += 3;
                    outputPointers.set(i, pointer);
                }
            }

            allIdle = true;
            for (int i = 0; i < computers.size(); i++) {
                if (!computers.get(i).isIdle()) {
              //      System.out.println("++ Computer " + i + " is not idle.");
                    allIdle = false;
                }
            }
        }
        System.out.println("-- STOPPING ");

        for (int i = 0; i < computers.size(); i++) {
            computers.get(i).stopComputer();
        }
    }
}
