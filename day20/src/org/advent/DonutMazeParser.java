package org.advent;

import org.advent.util.IntPoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonutMazeParser {
  //  private Map<String, DonutTeleporter> teleports;
    private Map<IntPoint, String> teleports;
    private char[][] map;

    public DonutMazeParser(String input) {
        this(Arrays.asList(input.split("\n")));
    }

    public DonutMazeParser(List<String> input) {
        // assume that the maze starts at 2x2
        int width = input.get(0).length() - 4;
        int height = input.size() - 4;

        map = new char[height][width];
        teleports = new HashMap<>();

        for (int y = 2; y < input.size() - 2; y++) {
            String line = input.get(y);
            for (int x = 2; x < line.length() - 2; x++) {

                char c = line.charAt(x);
                if (!isCharMaze(c)) {
                    map[y- 2][x - 2] = ' ';
                    continue;
                }
                map[y - 2][x - 2] = c;
                if (c == '.') {
                    checkForTeleporter(y, x, input);
                }
            }
        }

    }

    private void checkForTeleporter(int y, int x, List<String> input) {
        String key = null;
        if (!isCharMaze(input.get(y).charAt(x - 1))) {
            // left
            key = getKeyLeft(y, x, input);
        }
        if (!isCharMaze(input.get(y).charAt(x + 1))) {
            // right
            key = getKeyRight(y, x, input);
        }
        if (!isCharMaze(input.get(y - 1).charAt(x ))) {
            // upp
            key = getKeyUp(y, x, input);
        }
        if (!isCharMaze(input.get(y + 1).charAt(x))) {
            // down
            key = getKeyDown(y, x, input);
        }
        if (key != null) {
            teleports.put(new IntPoint(x - 2, y - 2), key);
            /*
            DonutTeleporter dt = teleports.get(key);
            if(dt == null) {
                dt = new DonutTeleporter(key, new IntPoint(x, y));
                teleports.put(key, dt);
            } else {
                dt.setB(new IntPoint(x, y));
            }*/
        }
    }

    private String getKeyUp(int y, int x, List<String> input) {
        return input.get(y - 2).charAt(x) + "" + input.get(y - 1).charAt(x);
    }

    private String getKeyDown(int y, int x, List<String> input) {
        return input.get(y + 1).charAt(x) + "" + input.get(y + 2).charAt(x);
    }

    private String getKeyRight(int y, int x, List<String> input) {
        return input.get(y).substring(x + 1, x + 3);
    }

    private String getKeyLeft(int y, int x, List<String> input) {
        return input.get(y).substring(x - 2, x);
    }

    private boolean isCharMaze(char c) {
        return c == '#' || c == '.';
    }

    public Map<IntPoint, String> getTeleports() {
        return teleports;
    }

    public char[][] getMap() {
        return map;
    }
}
