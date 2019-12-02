package org.advent;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        verify();
        ReadFile rf = new ReadFile();
        List<Integer> input = rf.getInput();
        input.stream().forEach(number -> System.out.println(number));
        int total = input.stream().reduce(0, (sum, number) -> sum += resolveRecursive(number), (sum1, sum2) -> sum1 + sum2);
        System.out.println("total: " + total);
    }

    private static void verify() {
        // step 1
        System.out.println("12 -> " + convert(12) + " (2)");
        System.out.println("14 -> " + convert(14) + " (2)");
        System.out.println("1969 -> " + convert(1969) + " (654)");
        System.out.println("100756 -> " + convert(100756) + " (33583)");

        // step 2
        System.out.println("12 -> " + resolveRecursive(12) + " (2)");
        System.out.println("14 -> " + resolveRecursive(14) + " (2)");
        System.out.println("1969 -> " + resolveRecursive(1969) + " (966)");
        System.out.println("100756 -> " + resolveRecursive(100756) + " (50346)");
    }

    // resolve for added
    private static int resolveRecursive(int base) {
        int needed = convert(base);
        if (needed <= 0) {
            return 0;
        }
        int total = needed + resolveRecursive(needed);
        return total;
    }

    // one step
    private static int convert(int number) {
        return Math.floorDiv(number, 3) - 2;
    }
}
