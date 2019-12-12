package org.advent;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();
        String input = "<x=14, y=9, z=14>\n" +
                "<x=9, y=11, z=6>\n" +
                "<x=-6, y=14, z=-4>\n" +
                "<x=4, y=-4, z=-3>";

        List<Moon> moons = MoonParser.parse(input);
        Simulator sim = new Simulator(moons);

        for(int i = 0; i < 1000; i++) {

            sim.simulateOnce();
        }

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

}
