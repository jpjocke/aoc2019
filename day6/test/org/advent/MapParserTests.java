package org.advent;

import com.advent.MapParser;
import com.advent.SpaceObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapParserTests {
    private static String testData = "COM)B\n" +
            "B)C\n" +
            "C)D\n" +
            "D)E\n" +
            "E)F\n" +
            "B)G\n" +
            "G)H\n" +
            "D)I\n" +
            "E)J\n" +
            "J)K\n" +
            "K)L";

    @Test
    public void ComIsFound() {
        MapParser parser = new MapParser(getTestData());
        SpaceObject root = parser.parseMap();
        Assert.assertEquals(MapParser.ROOT, root.getId());
    }

    public static List<String> getTestData() {
        List<String> data = new ArrayList<>();
        String[] split = testData.split("\n");
        Arrays.stream(split).forEach(d -> data.add(d));
        return data;
    }
}
