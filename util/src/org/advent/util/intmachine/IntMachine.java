package org.advent.util.intmachine;


import org.advent.util.intmachine.operations.Op;
import org.advent.util.intmachine.operations.OpFactory;

import java.util.List;

public class IntMachine {
    public static boolean DEBUG = false;
    int currentPos = 0;
    boolean isDone = false;
    Input input;
    private IntCodes intCodes;
    private Output output;
    private OpFactory factory;

    public IntMachine(IntCodes intCodes) {
        setup(intCodes, new Input());
    }

    public IntMachine(IntCodes intCodes, int input) {
        Input i = new Input();
        i.addInput(input);
        setup(intCodes, i);
    }

    public void resetOutput() {
        output.clear();
    }

    private void setup(IntCodes intCodes, Input input) {
        this.intCodes = intCodes;
        this.input = input;
        output = new Output();
        factory = new OpFactory(intCodes, output, input);
    }

    public void addInput(int input) {
        this.addInput(Long.valueOf(input));
    }

    public void addInput(long input) {
        this.input.addInput(input);
    }

    public long getLastOutput() {
        if (output.size() == 0) {
            return -1;
        }
        return output.getOutputAt(output.size() - 1);
    }

    public boolean isDone() {
        return isDone;
    }

    public void execute() {
     //   System.out.println("-- Executing");
      //  System.out.println("  " + intCodes);

        while (true) {
            Op op;
            try {
                op = factory.buildOp(currentPos);
            } catch (NoMoreInputException e) {
                if (DEBUG) {
                    System.out.println("-- Waiting for input");
                }
                // wait for next input
                break;
            }
            if (DEBUG) {
                System.out.println("- Run: " + currentPos + " -> " + intCodes.get(currentPos) + ", " + op + ", RelativeBase: " + intCodes.getRelativeBase());
                System.out.println("  " + intCodes);
            }
            if (op.isExit()) {

                if (DEBUG) {
                    System.out.println("### Found end at index: " + currentPos);
                }
                currentPos = 0;
                input.resetCounter();
                isDone = true;
                break;
            }
            int next = op.execute(currentPos, intCodes);
            currentPos = next;

            if (DEBUG) {
                System.out.println("  " + intCodes);
            }
        }

        if (DEBUG) {
            System.out.println("-- Result");
            System.out.println("  " + intCodes);
        }
    }

    public long getResult() {
        return intCodes.get(0);
    }

    public IntCodes getCodes() {
        return intCodes;
    }

    public List<Long> getOutput() {
        return output.getFullOutput();
    }

    public Output getOutputObj() {
        return output;
    }

}
