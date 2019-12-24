package org.advent;

import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;
import org.advent.util.intmachine.Output;


public class Computer extends Thread {
    private int index;
    private IntMachine im;
    private boolean stop;
    private boolean idle = false;

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

    public synchronized void addInput(long x, long y) {
        im.addInput(x, y);
        idle = false;
    }

    public boolean isIdle() {
        return idle;
    }

    @Override
    public void run() {
        System.out.println("Computer " + index + " started.");
        while (!stop) {
            if(!idle) {
                im.execute();
                im.addInput(-1);
                idle = true;
                im.execute();

            }
          //  System.out.println("Computer " + index + " idle (-1).");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Computer " + index + " stopped.");
    }
}
