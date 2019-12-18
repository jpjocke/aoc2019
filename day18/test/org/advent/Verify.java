package org.advent;

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
        d.print(parser.getPosition(), parser.getDoors(), parser.getKeys());
        Assert.assertEquals(2, 2);
    }
}
