package org.advent;


public class BugsMap {
    private boolean[][] map;

    public BugsMap(boolean[][] map) {
        this.map = map;
    }

    public void setValueAt(int x, int y, boolean value) {
        map[y][x] = value;
    }


    public void print() {
        for (int y = 0; y < map.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < map[y].length; x++) {
                sb.append(map[y][x] ? "#" : ".");
            }
            System.out.println(sb.toString());
        }
    }

    public void mutate() {
        boolean[][] nextMap = new boolean[5][5];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                byte count = adjacentBugs(y, x);
                if (map[y][x]) {
                    // bug here now
                    if (count == 1) {
                        nextMap[y][x] = true;
                    } else {
                        nextMap[y][x] = false;
                    }
                } else {
                    // empty
                    if (count == 1 || count == 2) {
                        nextMap[y][x] = true;
                    } else {
                        nextMap[y][x] = false;
                    }
                }
            }
        }
        map = nextMap;
    }

    private byte adjacentBugs(int y, int x) {
        byte count = 0;
        if (y > 0 && map[y - 1][x]) {
            count++;
        }
        if (y < 4 && map[y + 1][x]) {
            count++;
        }

        if (x > 0 && map[y][x - 1]) {
            count++;
        }

        if (x < 4 && map[y][x + 1]) {
            count++;
        }
        return count;
    }

    public int toValue() {
        int val = 0;
        int pointer = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x]) {
                    val |= (1 << pointer);
                }
                pointer++;
            }
        }
        return val;
    }
}
