package org.advent;

import org.advent.dfs.DfsCalc;
import org.junit.Assert;
import org.junit.Test;


public class Verify {
    @Test
    public void map1() {
        String input = "#########\n" +
                "#b.A.@.a#\n" +
                "#########";
        DungeonParser parser = new DungeonParser(input);
        Dungeon d = new Dungeon(parser.getMap());
        DfsCalc calc = new DfsCalc(parser.getKeys(), parser.getDoors(), d, parser.getPosition());
        int steps = calc.exploreForPos(parser.getKeys(), parser.getDoors(), parser.getPosition());

        Assert.assertEquals(8, steps);
    }

    @Test
    public void map2() {
        String input = "########################\n" +
                "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
                "######################.#\n" +
                "#d.....................#\n" +
                "########################";
        DungeonParser parser = new DungeonParser(input);
        Dungeon d = new Dungeon(parser.getMap());
        DfsCalc calc = new DfsCalc(parser.getKeys(), parser.getDoors(), d, parser.getPosition());
        int steps = calc.exploreForPos(parser.getKeys(), parser.getDoors(), parser.getPosition());

        Assert.assertEquals(86, steps);
    }

    @Test
    public void map3() {
        String input = "########################\n" +
                "#...............b.C.D.f#\n" +
                "#.######################\n" +
                "#.....@.a.B.c.d.A.e.F.g#\n" +
                "########################";
        DungeonParser parser = new DungeonParser(input);
        Dungeon d = new Dungeon(parser.getMap());
        DfsCalc calc = new DfsCalc(parser.getKeys(), parser.getDoors(), d, parser.getPosition());
        int steps = calc.exploreForPos(parser.getKeys(), parser.getDoors(), parser.getPosition());

        Assert.assertEquals(132, steps);
    }

    @Test
    public void map4() {
        // too slow
        String input = "#################\n" +
                "#i.G..c...e..H.p#\n" +
                "########.########\n" +
                "#j.A..b...f..D.o#\n" +
                "########@########\n" +
                "#k.E..a...g..B.n#\n" +
                "########.########\n" +
                "#l.F..d...h..C.m#\n" +
                "#################";
        DungeonParser parser = new DungeonParser(input);
        Dungeon d = new Dungeon(parser.getMap());
        DfsCalc calc = new DfsCalc(parser.getKeys(), parser.getDoors(), d, parser.getPosition());
        int steps = calc.exploreForPos(parser.getKeys(), parser.getDoors(), parser.getPosition());

        Assert.assertEquals(136, steps);
    }

    @Test
    public void map5() {
        String input = "########################\n" +
                "#@..............ac.GI.b#\n" +
                "###d#e#f################\n" +
                "###A#B#C################\n" +
                "###g#h#i################\n" +
                "########################";
        DungeonParser parser = new DungeonParser(input);
        Dungeon d = new Dungeon(parser.getMap());
        DfsCalc calc = new DfsCalc(parser.getKeys(), parser.getDoors(), d, parser.getPosition());
        calc.DEBUG = true;
        int steps = calc.exploreForPos(parser.getKeys(), parser.getDoors(), parser.getPosition());

        Assert.assertEquals(81, steps);
    }
}
