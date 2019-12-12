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

        for (int i = 0; i < 10; i++) {

            sim.simulateOnce();
        }

        Assert.assertEquals(2, moons.get(0).position.x);
        Assert.assertEquals(1, moons.get(0).position.y);
        Assert.assertEquals(-3, moons.get(0).position.z);

        Assert.assertEquals(-3, moons.get(0).velocity.x);
        Assert.assertEquals(-2, moons.get(0).velocity.y);
        Assert.assertEquals(1, moons.get(0).velocity.z);
    }

    @Test
    public void firstStep2() {
        String input = "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>";

        List<Moon> moons = MoonParser.parse(input);

        Simulator sim = new Simulator(moons);

      //  long simulations = sim.simulateUntilStateReached(true);
        long simulations = sim.simulateUntilStateReached(false);

        Assert.assertEquals(2772, simulations);
    }

    @Test
    public void secondStep2() {
        String input = "<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>";

        List<Moon> moons = MoonParser.parse(input);

        Simulator sim = new Simulator(moons);

        long simulations = sim.simulateUntilStateReached(false);

        Assert.assertEquals(4686774924L, simulations);
    }
}
