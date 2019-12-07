package org.advent.util.intmachine;


import org.advent.util.intmachine.operations.Op;
import org.advent.util.intmachine.operations.OpFactory;

import java.util.ArrayList;
import java.util.List;

public class IntMachine {
    private List<IntCode> codes;
    private List<Integer> output;
    private OpFactory factory;
    int currentPos = 0;
    boolean isDone = false;

    public IntMachine(List<IntCode> codes, int[] input) {
        this.codes = codes;
        output = new ArrayList<>();
        factory = new OpFactory(codes, output, input);
    }

    public IntMachine(List<IntCode> codes, int input) {
        this(codes, new int[]{input});
    }

    public IntMachine(List<IntCode> codes) {
        this(codes, new int[]{0});
    }

    public void setInputAndReset(int[] input) {
        output = new ArrayList<>();
        this.factory.setInputAndReset(input, output);
    }

    public boolean isDone() {
        return isDone;
    }

    public void execute() {
       execute(false);
    }

    public void execute(boolean stopOnFirstOutput) {
        System.out.println("-- Executing");
        printList(codes);

        while (true) {
            Op op = factory.buildOp(currentPos);
            System.out.println("- Run: " + currentPos + " -> " + codes.get(currentPos) + ", " + op);
            printList(codes);
            if (op.isExit()) {
                System.out.println("Found end at index: " + currentPos);
                currentPos = 0;
                isDone = true;
                break;
            }
            /*
            if (stopOnFirstOutput && op instanceof OpOutput) {
                currentPos = op.execute(currentPos, codes);
                break;
            }
             */
            int next = op.execute(currentPos, codes);
            if (next > codes.size()) {
                // if we dont find the exit
                break;
            }
            currentPos = next;
        }
        System.out.println("-- Result");
        printList(codes);
    }

    public int getResult() {
        return codes.get(0).getValue();
    }

    public List<IntCode> getCodes() {
        return codes;
    }

    public List<Integer> getOutput() {
        return output;
    }

    private void printList(List<IntCode> operations) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        operations.stream().forEach(op -> sb.append(op.toString() + ", "));
        System.out.println(sb.toString());
    }
}
