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

        // step1();
        step2();

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

    private static void step1() {
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
    }

    public static void step2() {
        BugsMapStep2 map = new BugsMapStep2(BugsMapParser.parse(input));
        BugsMapInfinite inf = new BugsMapInfinite();
        inf.setLevel0(map);
        inf.print();

        for (int i = 0; i < 200; i ++) {
            inf.mutate();
           // inf.print();
        }

        System.out.println("After 200 min: " + inf.countAll());
    }
}
