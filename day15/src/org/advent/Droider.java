package org.advent;

import java.io.IOException;

public class Droider {
    private DroidMode current;
    private DroidMode explore;
    private DroidMode right;
    private DroidMode manual;
    private Droid d;

    public Droider(Labyrinth labyrinth, Droid d) {
        manual = new DroidManualMode(labyrinth, d);
        explore = new DroidExploreMode(labyrinth, d);
        right = new DroidAlwaysRightMode(labyrinth, d);
        current = manual;
        this.d = d;
    }

    public void run() throws IOException {
        int iterator = 0;
        while (true) {
            int dir = current.getNextDir();
            if (dir == -1) {
                break;
            }
            if (dir == 5) {
                continue;
            }
            if (dir == 50) {
                iterator = 0;
                current = explore;
                dir = current.getNextDir();
            }
            if (dir == 60) {
                iterator = 0;
                current = right;
                dir = current.getNextDir();
            }
            iterator++;
            if (current instanceof DroidManualMode) {
                int ret = d.runAndReport(dir);
                while (ret != 0) {
                    ret = d.runAndReport(dir);
                }
            } else {

                d.runAndReport(dir);
            }

            if (iterator > 10) {
                current = manual;
            }
        }
    }
}
