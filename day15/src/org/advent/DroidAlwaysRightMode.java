package org.advent;

import org.advent.util.IntPoint;

import java.util.Arrays;

public class DroidAlwaysRightMode implements DroidMode {
    Labyrinth labyrinth;
    Droid d;
    int direction; // 1 up, 2 down, 3 left, 4 right
    IntPoint lastPosition;
    int[] lastAround;

    public DroidAlwaysRightMode(Labyrinth labyrinth, Droid d) {
        this.labyrinth = labyrinth;
        this.d = d;
        direction = 1;
        lastPosition = null;
        lastAround = null;
    }

    public int getNextDir() {
        // ALWAYS GO RIGHT, KEEP TRACK OF DIRECTION!

        // is this correct?
        // starting point
        if (lastPosition == null && lastAround == null) {
            lastPosition = d.getPosition().copy();
            int[] around = buildAround();
            lastAround = around;
            return getDirectionFromAround(around);
        }

        // Did we move?
        if (lastPosition.equals(d.getPosition())) {
            int[] around = buildAround();
            return getDirectionFromAround(around);
            //todo
        }

        // Go back if we moved & did not fully explore last
        if (lastAround[0] == -1 || lastAround[1] == -1 || lastAround[2] == -1 || lastAround[3] == -1) {
            return getOppositeDir(direction);
        }

        return direction;
    }

    private int[] buildAround() {
        IntPoint fakePos = d.getPosition().copy();
        int[] around = new int[4];
        // up
        fakePos.y--;
        around[0] = labyrinth.getValueAt(fakePos);
        // down
        fakePos.y++;
        fakePos.y++;
        around[1] = labyrinth.getValueAt(fakePos);
        // left
        fakePos.y--;
        fakePos.x--;
        around[2] = labyrinth.getValueAt(fakePos);
        // right
        fakePos.x++;
        fakePos.x++;
        around[3] = labyrinth.getValueAt(fakePos);
        return around;
    }

    private int getOppositeDir(int dir) {
        if (dir == 1) {
            return 2;
        }
        if (dir == 2) {
            return 1;
        }
        if (dir == 3) {
            return 4;
        }
        return 3;
    }

    private int getDirectionFromAround(int[] around) {
        if (around[0] == -1) {
            return 1;
        }
        if (around[1] == -1) {
            return 2;
        }

        if (around[2] == -1) {
            return 3;
        }

        if (around[3] == -1) {
            return 4;
        }

        return direction;
    }
}
