package org.advent;


public class BugsMapStep2 {
    private boolean[][] map;
    private boolean[][] nextMap;

    public BugsMapStep2(boolean[][] map) {
        this.map = map;
    }

    public BugsMapStep2() {
        map = new boolean[5][5];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = false;
            }
        }
    }

    public void setValueAt(int x, int y, boolean value) {
        map[y][x] = value;
    }


    public void print() {
        for (int y = 0; y < map.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < map[y].length; x++) {
                if (y == 2 && x == 2) {
                    sb.append("?");
                    continue;
                }
                sb.append(map[y][x] ? "#" : ".");
            }
            System.out.println(sb.toString());
        }
    }

    public void mutate(BugsCounter bc, int level) {
        nextMap = new boolean[5][5];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (x == 2 && y == 2) {
                    continue;
                }

                int count = bc.countForPos(level, y, x);
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
    }

    public boolean valueAt(int y, int x) {
        return map[y][x];
    }

    public boolean anyOuter() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (y == 0 || x == 0 || y == map.length - 1 || x == map[y].length - 1) {
                    if (map[y][x]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean anyInner() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (y == 2 || x == 2 || y == 3 || x == 3) {
                    if (map[y][x]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void nextMap() {
        map = nextMap;
    }

    public int countBugs() {
        int count = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if(map[y][x]) {
                    count++;
                }
            }
        }
        return count;
    }
}
