package org.advent;

import org.advent.util.file.ReadFile;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();
        List<String> input = ReadFile.readFileAsStringsPerLine("/home/jocke/jProjects/advent/day18/resources/day18_in.txt");

        DungeonParser parser = new DungeonParser(input);
        Dungeon d = new Dungeon(parser.getMap());

        d.print(parser.getPosition(), parser.getDoors(), parser.getKeys());

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

}
