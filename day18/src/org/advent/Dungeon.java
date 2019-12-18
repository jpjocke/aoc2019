package org.advent;

import org.advent.dfs.DfsNode;
import org.advent.util.IntPoint;
import org.advent.util.Util;

import java.util.Map;
import java.util.Optional;

public class Dungeon {
    private char[][] map;

    public Dungeon(char[][] map) {
        this.map = map;
    }

    public char getCharAt(IntPoint pos) {
        return map[pos.y][pos.x];
    }

    public char getCharAt(int x, int y) {
        return map[y][x];
    }

    public void print(IntPoint position, Map<IntPoint, Character> keys, Map<IntPoint, Character> doors) {
        IntPoint key = new IntPoint(0, 0);
        for (int y = 0; y < map.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < map[y].length; x++) {
                key.x = x;
                key.y = y;
                if (key.equals(position)) {
                    sb.append('@');

                } else if (doors.containsKey(key)) {
                    sb.append(doors.get(key));
                } else if (keys.containsKey(key)) {
                    sb.append(keys.get(key));
                } else {

                    sb.append(map[y][x]);
                }
            }
            System.out.println(sb.toString());
        }
    }

    public void print(IntPoint position, Map<IntPoint, Character> keys, Map<IntPoint, Character> doors, DfsNode top) {
        IntPoint key = new IntPoint(0, 0);
        for (int y = 0; y < map.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < map[y].length; x++) {
                key.x = x;
                key.y = y;
                Optional<DfsNode> steps = top.findByPosition(key);
                if (key.equals(position)) {
                    sb.append(toFourChar('@'));

                } else if (doors.containsKey(key)) {
                    sb.append(toFourChar(doors.get(key)));
                } else if (keys.containsKey(key)) {
                    sb.append(toFourChar(keys.get(key)));
                } else if(steps.isPresent()) {
                    sb.append(toFourInt(steps.get().getSteps()));
                }else {

                    sb.append(toFourChar(map[y][x]));
                }
            }
            System.out.println(sb.toString());
        }
    }

    private String toFourChar(char c) {
        return "   " + c;
    }

    private String toFourInt(int c) {
        int[] digits = Util.toDigits(c, 3);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        sb.append("|");
        return sb.toString();
    }
}
