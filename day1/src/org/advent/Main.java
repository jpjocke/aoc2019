package org.advent;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        verify();
        ReadFile rf = new ReadFile();
        List<Integer> input = rf.getInput();
        input.stream().forEach(number -> System.out.println(number));
        int total = input.stream().reduce(0, (sum, number) -> sum += convert(number), (sum1, sum2) -> sum1 + sum2);
        System.out.println(total);
    }

    private static void verify() {
        System.out.println("12 -> " + convert(12) + " (2)");
        System.out.println("14 -> " + convert(14) + " (2)");
        System.out.println("1969 -> " + convert(1969) + " (654)");
        System.out.println("100756 -> " + convert(100756) + " (33583)");
    }

    private static int convert(int number) {
        return Math.floorDiv(number, 3) - 2;
    }
}
