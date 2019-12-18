package org.advent;

import org.advent.util.IntPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonParser {
    private Map<IntPoint, Character> keys;
    private Map<IntPoint, Character> doors;
    private char[][] map;
    private IntPoint position;

    public DungeonParser(String input) {
        this(Arrays.asList(input.split("\n")));
    }

    // A = 65
    // a = 97

    public DungeonParser(List<String> input) {
        int width = input.get(0).length();
        int height = input.size();
        keys = new HashMap<>();
        doors = new HashMap<>();
        map = new char[height][width];
        for (int y = 0; y < height; y++) {
            String line = input.get(y);
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                if (c == '@') {
                    position = new IntPoint(x, y);
                    map[y][x] = '.';
                } else if (c >= 65) {
                    if (c < 97) {
                        doors.put(new IntPoint(x, y), c);
                    } else {
                        keys.put(new IntPoint(x, y), c);
                    }
                    map[y][x] = '.';
                } else {
                    map[y][x] = line.charAt(x);
                }
            }
        }
    }

    public Map<IntPoint, Character> getKeys() {
        return keys;
    }

    public Map<IntPoint, Character> getDoors() {
        return doors;
    }

    public List<Character> getDoorsAsList() {
        List<Character> doorsList = new ArrayList<>();
        for (IntPoint p : doors.keySet()) {
            doorsList.add(doors.get(p));
        }
        return doorsList;
    }

    public char[][] getMap() {
        return map;
    }

    public IntPoint getPosition() {
        return position;
    }
}
