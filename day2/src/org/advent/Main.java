package org.advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static String base = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,5,19,23,2,10,23,27,1,27,5,31,2,9,31,35,1,35,5,39,2,6,39,43,1,43,5,47,2,47,10,51,2,51,6,55,1,5,55,59,2,10,59,63,1,63,6,67,2,67,6,71,1,71,5,75,1,13,75,79,1,6,79,83,2,83,13,87,1,87,6,91,1,10,91,95,1,95,9,99,2,99,13,103,1,103,6,107,2,107,6,111,1,111,2,115,1,115,13,0,99,2,0,14,0";
    private static String input_extra = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,5,19,23,2,10,23,27,1,27,5,31,2,9,31,35,1,35,5,39,2,6,39,43,1,43,5,47,2,47,10,51,2,51,6,55,1,5,55,59,2,10,59,63,1,63,6,67,2,67,6,71,1,71,5,75,1,13,75,79,1,6,79,83,2,83,13,87,1,87,6,91,1,10,91,95,1,95,9,99,2,99,13,103,1,103,6,107,2,107,6,111,1,111,2,115,1,115,13,0,99,2,0,14,0";

    public static void main(String[] args) {
        verify();

        System.out.println("#######################################");
        System.out.println("REAL");
        List<Op> operation = toOperations(base);
        execute(operation);
        System.out.println("#######################################");


        System.out.println("#######################################");
        System.out.println("REAL-2");
        operation = toOperations(input_extra);
        execute(operation);
        System.out.println("#######################################");

        step2Problem();
    }


    private static void verify() {
        System.out.println("#######################################");
        System.out.println("1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2)");
        List<Op> operation1 = toOperations("1,0,0,0,99");
        execute(operation1);

        System.out.println("#######################################");
        System.out.println("2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6)");
        List<Op> operation2 = toOperations("2,3,0,3,99");
        execute(operation2);

        System.out.println("#######################################");
        System.out.println("2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801)");
        List<Op> operation3 = toOperations("2,4,4,5,99,0");
        execute(operation3);

        System.out.println("#######################################");
        System.out.println("1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99");
        List<Op> operation4 = toOperations("1,1,1,4,99,5,6,0,99");
        execute(operation4);
    }

    private static void step2Problem() {
        int wanted = 19690720;
        int a = 0;
        int b = 0;
        boolean notDone = true;

        List<Op> operation;
        while (notDone) {
            outerloop:
            for (int i = 0; i <= 99; i++) {
                a = i;
                for (int j = 0; j <= 99; j++) {
                    b = j;
                    operation = toOperations(base);
                    operation.get(1).setValue(a);
                    operation.get(2).setValue(b);
                    execute(operation);
                    if (operation.get(0).getValue() == wanted) {
                        notDone = false;
                        break outerloop;
                    }
                }
            }


        }

        System.out.println("#######################################");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("result: " + (100 * a + b));

    }

    private static List<Op> toOperations(String string) {
        String[] split = string.split(",");
        return Arrays.stream(split)
                .map(s -> new Op(Integer.parseInt(s)))
                .collect(Collectors.toList());
    }

    private static void execute(List<Op> operations) {
        int current = 0;
        System.out.println("-- Executing");
        printList(operations);

        while (true) {
            if (operations.get(current).isExit()) {
                //  System.out.println("Found end at index: " + current);
                break;
            }
            operations.get(current).executeOp(operations, current);
            current += 4;
        }
        System.out.println("-- Result");
        printList(operations);

    }

    private static void printList(List<Op> operations) {
        StringBuilder sb = new StringBuilder();
        operations.stream().forEach(op -> sb.append(op.toString() + ", "));
        System.out.println(sb.toString());
    }
}
