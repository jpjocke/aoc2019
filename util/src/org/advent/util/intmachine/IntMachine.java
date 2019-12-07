package org.advent.util.intmachine;


import org.advent.util.intmachine.operations.Op;
import org.advent.util.intmachine.operations.OpFactory;

import java.util.ArrayList;
import java.util.List;

public class IntMachine {
    private List<IntCode> codes;
    private List<Integer> output;
    private OpFactory factory;

    public IntMachine(List<IntCode> codes, int input) {
        this.codes = codes;
        output = new ArrayList<>();
        factory = new OpFactory(codes, output, input);
    }

    public IntMachine(List<IntCode> codes) {
        this(codes, 0);
    }

    public void execute() {
        int current = 0;
        System.out.println("-- Executing");
        printList(codes);

        while (true) {
            System.out.println("- update -");
            System.out.println("current: " + current);
            printList(codes);
            System.out.println(codes.get(current));
            Op op = factory.buildOp(current);
            if (op.isExit()) {
                System.out.println("Found end at index: " + current);
                break;
            }
            current = op.execute(current, codes);
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
        operations.stream().forEach(op -> sb.append(op.toString() + ", "));
        System.out.println(sb.toString());
    }
}
