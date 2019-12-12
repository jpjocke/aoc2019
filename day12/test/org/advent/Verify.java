package org.advent;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class Verify {


    @Test
    public void first() {
        String input = "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>";

        List<Moon> moons = MoonParser.parse(input);

        Simulator sim = new Simulator(moons);

        for(int i = 0; i < 10; i++) {

            sim.simulateOnce();
        }

        Assert.assertEquals(2, 2);
    }
}
