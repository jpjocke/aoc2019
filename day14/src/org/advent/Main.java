package org.advent;

import org.advent.util.file.ReadFile;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();

        List<String> lines = ReadFile.readFileAsStringsPerLine("/home/jocke/jProjects/advent/day14/resources/day14_in.txt");

        List<Reaction> reactions = ReactionParser.parse(lines);
        long max = 1000000000000L;
        long fuel = 5500000000L;
        long found = 0;
        while (found < max) {
            MaterialCalculator calc = new MaterialCalculator(reactions);
            calc.DEBUG = true;
            found = calc.oreNeededForOneFuelTwo(fuel);
            System.out.println("fuel: " + fuel + " = " + found);
            fuel++;

        }

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

}
