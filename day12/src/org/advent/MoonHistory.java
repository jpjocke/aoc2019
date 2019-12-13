package org.advent;

import java.util.Arrays;
import java.util.List;

public class MoonHistory {
    int mode;
    int[] start;
    boolean found = false;
    long cycles = -1;

    public MoonHistory(int mode) {
        this.mode = mode;
    }

    public void haveWeBeenHereBefore(long simulations, List<Moon> moons) {
        if (simulations == 0) {
            start = toPos(moons);
            return;
        }

        int[] now = toPos(moons);

        if (Arrays.equals(start, now)) {
            found = true;
            System.out.println("mode: " + mode + ", sim: " + simulations + ", val: " + Arrays.toString(now));
            cycles = simulations;
        }
    }

    private int[] toPos(List<Moon> moons) {
        int[] pos;
        if (mode == 1) {
            pos = new int[]{
                    moons.get(0).position.x,
                    moons.get(0).velocity.x,
                    moons.get(1).position.x,
                    moons.get(1).velocity.x,
                    moons.get(2).position.x,
                    moons.get(2).velocity.x,
                    moons.get(3).position.x,
                    moons.get(3).velocity.x
            };
        } else if (mode == 2) {
            pos = new int[]{
                    moons.get(0).position.y,
                    moons.get(0).velocity.y,
                    moons.get(1).position.y,
                    moons.get(1).velocity.y,
                    moons.get(2).position.y,
                    moons.get(2).velocity.y,
                    moons.get(3).position.y,
                    moons.get(3).velocity.y
            };
        } else {
            pos = new int[]{
                    moons.get(0).position.z,
                    moons.get(0).velocity.z,
                    moons.get(1).position.z,
                    moons.get(1).velocity.z,
                    moons.get(2).position.z,
                    moons.get(2).velocity.z,
                    moons.get(3).position.z,
                    moons.get(3).velocity.z
            };
        }
        return pos;
    }
}
