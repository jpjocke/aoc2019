package org.advent;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static String input = "..#..\n" +
            "##..#\n" +
            "##...\n" +
            "#####\n" +
            ".#.##";

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();

        Map<Integer, Integer> previous = new HashMap<>();

        BugsMap map = new BugsMap(BugsMapParser.parse(input));

        while (true) {
            Integer prev = previous.get(map.toValue());
            if (prev != null) {
                System.out.println("Found: " + prev);
                break;
            }
            int val = map.toValue();
            System.out.println("Val: " + val);
            previous.put(val, val);
            map.mutate();
        }

        // 3174811 too high
        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

}
