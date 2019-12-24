package org.advent;

import java.util.HashMap;
import java.util.Map;

public class BugsMapInfinite implements BugsCounter {
    Map<Integer, BugsMapStep2> maps;
    private int min;
    private int max;

    public BugsMapInfinite() {
        maps = new HashMap<>();
        min = 0;
        max = 0;
    }

    public void setLevel0(BugsMapStep2 map) {
        maps.put(0, map);
    }

    public void mutate() {
        int down = 0;
        int up = 1;
        while (true) {
            if (down >= min) {
                BugsMapStep2 map = maps.get(down);
                if (down == min && map.anyInner()) {
                    min--;
                    maps.put(min, new BugsMapStep2());
                }
                if (down == max && map.anyOuter()) {
                    max++;
                    maps.put(max, new BugsMapStep2());
                }
                map.mutate(this, down);
            }

            if (up <= max) {
                BugsMapStep2 map = maps.get(up);
                if (up == max && map.anyOuter()) {
                    max++;
                    maps.put(max, new BugsMapStep2());
                }
                map.mutate(this, up);
            }


            down--;
            up++;
            if (up > max && down < min) {
                break;
            }
        }

        // rotate maps
        for (Integer key : maps.keySet()) {
            maps.get(key).nextMap();
        }
    }

    public int countForPos(int level, int y, int x) {
        BugsMapStep2 map = maps.get(level);
        BugsMapStep2 up = maps.get(level + 1);
        BugsMapStep2 down = maps.get(level - 1);

        int count = 0;
        int upP = y - 1;
        int downP = y + 1;
        int leftP = x - 1;
        int rightP = x + 1;

        if (upP == -1) {
            // outer
            if (up != null) {
                    if (up.valueAt(1, 2)) {
                        count++;
                }
            }
        } else if (upP == 2 && x == 2) {
            // inner
            if (down != null) {
                for( int i = 0; i < 5 ; i++) {
                    if (down.valueAt(4, i)) {
                        count++;
                    }
                }
            }
        } else {
            if (map.valueAt(upP, x)) {
                count++;
            }
        }

        if (downP == 5) {
            // outer
            if (up != null) {
                if (up.valueAt(3, 2)) {
                    count++;
                }
            }
        } else if (downP == 2 && x == 2) {
            // inner
            if (down != null) {
                for( int i = 0; i < 5 ; i++) {
                    if (down.valueAt(0, i)) {
                        count++;
                    }
                }
            }
        } else {
            if (map.valueAt(downP, x)) {
                count++;
            }
        }

        if (leftP == -1) {
            // outer
            if (up != null) {
                if (up.valueAt(2, 1)) {
                    count++;
                }
            }
        } else if (leftP == 2 && y == 2) {
            // inner
            if (down != null) {
                for( int i = 0; i < 5 ; i++) {
                    if (down.valueAt(i, 4)) {
                        count++;
                    }
                }
            }
        } else {
            if (map.valueAt(y, leftP)) {
                count++;
            }
        }

        if (rightP == 5) {
            // outer
            if (up != null) {
                if (up.valueAt(2, 3)) {
                    count++;
                }
            }
        } else if (rightP == 2 && y == 2) {
            // inner
            if (down != null) {
                for( int i = 0; i < 5 ; i++) {
                    if (down.valueAt(i, 0)) {
                        count++;
                    }
                }
            }
        } else {
            if (map.valueAt(y, rightP)) {
                count++;
            }
        }

        return count;
    }

    public void print() {
        System.out.println("-----------------------");
        System.out.println("------------- Print all");
        int pointer = min;
        while (pointer <= max) {
            System.out.println("Level: " + pointer);
            maps.get(pointer).print();
            pointer++;
        }
        System.out.println("-----------------------");
    }

    public int countAll() {
        int count = 0;
        int pointer = min;
        while (pointer <= max) {
            count += maps.get(pointer).countBugs();
            pointer++;
        }
        return count;
    }
}
