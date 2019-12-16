package org.advent;

import org.advent.util.IntPoint;

import java.io.IOException;

public class DroiderRight {
    private DroidMode right;
    private Droid d;

    public DroiderRight(Labyrinth labyrinth, Droid d) {
        right = new DroidAlwaysRightMode(labyrinth, d);
        this.d = d;
    }

    public void run() throws IOException {
        int iterator = 0;
        IntPoint start = d.getPosition().copy();
        while (true) {
            int dir = right.getNextDir();
            d.runAndReport(dir);
            iterator++;
            if (iterator > 10 && start.equals(d.getPosition())) {
                break;
            }
        }
    }
}
