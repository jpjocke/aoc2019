package org.advent;

import java.math.BigInteger;
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
      //  step1(sim);
        step2(sim);


        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

    private static void step1(Simulator sim) {
        for(int i = 0; i < 1000; i++) {

            sim.simulateOnce();
        }

    }

    private static void step2(Simulator sim) {

        BigInteger simulations = sim.simulateUntilStateReached(false);
        System.out.println("simulations: " + simulations);
        // 8482595013102893415949853418600 too high
        // 3238446226353494574600 too high
        // 3786061230652550826857400 too high
    }

}
