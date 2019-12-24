package org.advent;

public class BugsMapParser {

    public static boolean[][] parse(String input) {
        String[] split = input.split("\n");
        boolean[][] map = new boolean[5][5];
        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split[i].length(); j++) {
                if (split[i].charAt(j) == '.') {
                    map[i][j] = false;
                } else {
                    map[i][j] = true;
                }
            }
        }
        return map;
    }
}
