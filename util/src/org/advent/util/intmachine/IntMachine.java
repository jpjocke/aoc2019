package org.advent.util.intmachine;


import org.advent.util.intmachine.operations.Op;
import org.advent.util.intmachine.operations.OpFactory;

import java.util.ArrayList;
import java.util.List;

public class IntMachine {
    int currentPos = 0;
    boolean isDone = false;
    Input input;
    private List<IntCode> codes;
    private List<Integer> output;
    private OpFactory factory;


    public IntMachine(List<IntCode> codes) {
        setup(codes, new Input());
    }

    public IntMachine(List<IntCode> codes, int input) {
        Input i = new Input();
        i.addInput(input);
        setup(codes, i);
    }

    private void setup(List<IntCode> codes, Input input) {
        this.codes = codes;
        this.input = input;
        output = new ArrayList<>();
        factory = new OpFactory(codes, output, input);
    }

    public void addInput(int input) {
        this.input.addInput(input);
    }

    public int getLastOutput() {
        if (output.size() == 0) {
            return -1;
        }
        return output.get(output.size() - 1);
    }

    public boolean isDone() {
        return isDone;
    }

    public void execute() {
        System.out.println("-- Executing");
        printList(codes);

        while (true) {
            Op op;
            try {
                op = factory.buildOp(currentPos);
            } catch (NoMoreInputException e) {
                // wait for next input
                break;
            }
            System.out.println("- Run: " + currentPos + " -> " + codes.get(currentPos) + ", " + op);
            printList(codes);
            if (op.isExit()) {
                System.out.println("### Found end at index: " + currentPos);
                currentPos = 0;
                input.resetCounter();
                isDone = true;
                break;
            }
            int next = op.execute(currentPos, codes);
            if (next > codes.size()) {
                // if we dont find the exit
                break;
            }
            currentPos = next;
            printList(codes);
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
