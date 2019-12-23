package org.advent;

import org.advent.util.intmachine.Output;

import java.util.ArrayList;
import java.util.List;

public class OutputCoordinator extends Thread {
    private List<Computer> computers;
    private List<Output> outputs;
    private List<Integer> outputPointers;

    public OutputCoordinator(int size) {
        outputs = new ArrayList<>();
        outputPointers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            outputPointers.add(0);
        }
        computers = new ArrayList<>();
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

        while (!stop) {
            for (int i = 0; i < outputPointers.size(); i++) {
                int pointer = outputPointers.get(i);
                Output o = outputs.get(i);
                if (o.size() >= pointer + 3) {
                    int to = (int)o.getOutputAt(pointer);
                    long x = o.getOutputAt(pointer + 1);
                    long y = o.getOutputAt(pointer + 2);
                    System.out.println("-- Output on index " + i + ", " + pointer + "/" + outputs.size());

                    System.out.println("-- To " + to + ", " + x + ", " + y);
                    if (to == 255) {
                        System.out.println("######## To " + to + ", " + x + ", " + y);
                        stop = true;
                        break;
                    } else {
                        computers.get(to).addInput(x);
                        computers.get(to).addInput(y);
                    }
                    pointer += 3;
                    outputPointers.set(i, pointer);
                }
            }
        }
        System.out.println("-- STOPPING ");

        for (int i = 0; i < computers.size(); i++) {
            computers.get(i).stopComputer();
        }
    }
}
