package org.advent;

import com.advent.MapCalculator;
import com.advent.MapParser;
import com.advent.SpaceObject;
import org.junit.Assert;
import org.junit.Test;


public class VerifyTests {

    @Test
    public void verifyOrbits() {
        MapParser parser = new MapParser(MapParserTests.getTestData());
        SpaceObject root = parser.parseMap();
        int orbits = MapCalculator.calculateTotalOrbits(root);
        Assert.assertEquals(42, orbits);
    }

    @Test
    public void verifyJumps() {
        MapParser parser = new MapParser(MapParserTests.getHopData());
        SpaceObject root = parser.parseMap();
        int jumps = MapCalculator.calculateJumps(root, "SAN", "YOU");
        Assert.assertEquals(4, jumps);
    }

}
