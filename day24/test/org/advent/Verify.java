package org.advent;

import org.junit.Assert;
import org.junit.Test;



public class Verify {


    @Test
    public void map1() {
        String input = "....#\n" +
                "#..#.\n" +
                "#..##\n" +
                "..#..\n" +
                "#....";

        BugsMap map = new BugsMap(BugsMapParser.parse(input));
        Assert.assertEquals(1205552, map.toValue());

        map.print();;
        map.mutate();
        Assert.assertEquals(7200233, map.toValue());
        map.print();;

        map.mutate();
        Assert.assertEquals(30687775, map.toValue());
        map.print();;


        map.mutate();
        Assert.assertEquals(23519713, map.toValue());
        map.print();;

        map.mutate();
        Assert.assertEquals(3165711, map.toValue());
        map.print();;
    }

    @Test
    public void valueCheck() {
        String input = ".....\n" +
                ".....\n" +
                ".....\n" +
                "#....\n" +
                ".#...";

        BugsMap map = new BugsMap(BugsMapParser.parse(input));

        Assert.assertEquals(2129920, map.toValue());
    }

    @Test
    public void testStep2() {
        String input = "....#\n" +
                "#..#.\n" +
                "#..##\n" +
                "..#..\n" +
                "#....";

        BugsMapStep2 map = new BugsMapStep2(BugsMapParser.parse(input));
        BugsMapInfinite inf = new BugsMapInfinite();
        inf.setLevel0(map);
        inf.print();

        for (int i = 0; i < 10; i ++) {
            inf.mutate();
            inf.print();
        }
        Assert.assertEquals(99, inf.countAll());
    }
}
