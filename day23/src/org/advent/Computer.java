package org.advent;

import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;
import org.advent.util.intmachine.Output;


public class Computer extends Thread {
    private int index;
    private IntMachine im;
    private boolean stop;

    public Computer(int index, String input) {

        System.out.println("Computer " + index + " created.");
        IntCodes ops = new IntCodes(input);
        im = new IntMachine(ops);
        im.addInput(index);
        this.index = index;
        stop = false;
    }

    public Output getOutput() {
        return im.getOutputObj();
    }

    public void stopComputer() {
        this.stop = true;
    }

    public synchronized void addInput(long input) {
        im.addInput(input);
    }

    @Override
    public void run() {
        System.out.println("Computer " + index + " started.");
        while (!stop) {
            im.execute();
            System.out.println("Computer " + index + " idle (-1).");
            im.addInput(-1);
        }
        System.out.println("Computer " + index + " stopped.");
    }
}
