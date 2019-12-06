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

    private static String hopData = "COM)B\n" +
            "B)C\n" +
            "C)D\n" +
            "D)E\n" +
            "E)F\n" +
            "B)G\n" +
            "G)H\n" +
            "D)I\n" +
            "E)J\n" +
            "J)K\n" +
            "K)L\n" +
            "K)YOU\n" +
            "I)SAN";

    @Test
    public void ComIsFound() {
        MapParser parser = new MapParser(getTestData());
        SpaceObject root = parser.parseMap();
        Assert.assertEquals(MapParser.ROOT, root.getId());
    }

    public static List<String> getTestData() {
        return getData(testData);
    }

    public static List<String> getHopData() {
        return getData(hopData);
    }

    private static List<String> getData(String dataS) {
        List<String> data = new ArrayList<>();
        String[] split = dataS.split("\n");
        Arrays.stream(split).forEach(d -> data.add(d));
        return data;
    }
}
