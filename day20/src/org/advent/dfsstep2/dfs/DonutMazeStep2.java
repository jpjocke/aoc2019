package org.advent.dfsstep2.dfs;

import org.advent.dfs.DfsNodeDonut;
import org.advent.util.IntPoint;
import org.advent.util.Util;

import java.util.Map;
import java.util.Optional;

public class DonutMazeStep2 {
    private char[][] map;

    public DonutMazeStep2(char[][] map) {
        this.map = map;
    }

    public char getCharAt(IntPoint pos) {
        if (pos.x < 0 || pos.y < 0 || pos.x >= map[0].length || pos.y >= map.length) {
            return '#';
        }
        return map[pos.y][pos.x];
    }

    public char getCharAt(int x, int y) {
        return map[y][x];
    }

    public void print(Map<IntPoint, String> teleports) {
        IntPoint key = new IntPoint(0, 0);
        for (int y = 0; y < map.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < map[y].length; x++) {
                key.x = x;
                key.y = y;
                if (teleports.get(key) != null) {
                    sb.append('@');
                } else {

                    sb.append(map[y][x]);
                }
            }
            System.out.println(sb.toString());
        }
    }

    public void printFull(DfsNodeDonut2 top, IntPoint pos, int levels) {

        IntPoint key = new IntPoint(0, 0);
        for(int i = 0; i < levels; i++) {
            print(top, pos, i);
        }

    }

    public void print(DfsNodeDonut2 top, IntPoint pos, int level) {

        IntPoint key = new IntPoint(0, 0);
            System.out.println("----------- level " + level + " -----------------------");
            for (int y = 0; y < map.length; y++) {
                StringBuilder sb = new StringBuilder();
                for (int x = 0; x < map[y].length; x++) {
                    key.x = x;
                    key.y = y;
                    // TODO: if print all levels?
                    Optional<DfsNodeDonut2> steps = top.findByPosition(key, level);
                    if(key.equals(pos)) {
                        sb.append(" ^^ ");
                    }
                    else if (steps.isPresent()) {
                        sb.append(toFourInt(steps.get().getSteps()));
                    } else {

                        sb.append(toFourChar(map[y][x]));
                    }
                }
                System.out.println(sb.toString());
            }

    }

    private String toFourChar(char c) {
        if (c == '#') {
            return "####";
        }
        return "   " + c;
    }

    private String toFourInt(int c) {
        int[] digits = Util.toDigits(c, 3);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        sb.append("|");
        return sb.toString();
    }
}
