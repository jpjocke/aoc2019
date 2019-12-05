package org.advent;

import org.advent.operations.Op;
import org.advent.operations.OpFactory;

import java.util.ArrayList;
import java.util.List;

public class IntMachine {
    private List<IntCode> codes;
    private List<Integer> output;

    public IntMachine(List<IntCode> codes) {
        this.codes = codes;
        output = new ArrayList<>();
    }

    public void execute() {
        int current = 0;
        System.out.println("-- Executing");
        printList(codes);

        while (true) {
            Op op = OpFactory.buildOp(current, codes, output);
            if (op.isExit()) {
                System.out.println("Found end at index: " + current);
                break;
            }
            current += op.execute(codes);
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
