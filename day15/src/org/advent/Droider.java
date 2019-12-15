package org.advent;

import java.io.IOException;

public class Droider {
    private DroidMode current;
    private DroidMode explore;
    private DroidMode manual;
    private Droid d;

    public Droider(Labyrinth labyrinth, Droid d) {
        manual = new DroidManualMode(labyrinth, d);
        explore = new DroidExploreMode(labyrinth, d);
        current = manual;
        this.d = d;
    }

    public void run() throws IOException {
        // måste kunna gå ett steg åt gången, skönt att kunna hoppa hela längder med
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
            if (iterator > 10) {
                current = manual;
            }
            iterator++;
            if (current instanceof DroidManualMode) {
                int ret = d.runAndReport(dir);
                while (ret != 0) {
                    ret = d.runAndReport(dir);
                }
            }
            else {

                d.runAndReport(dir);
            }
        }
    }
}
