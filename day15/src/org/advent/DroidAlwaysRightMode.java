package org.advent;

import org.advent.util.IntPoint;

public class DroidAlwaysRightMode implements DroidMode {
    Labyrinth labyrinth;
    Droid d;
    int direction; // 1 up, 2 down, 3 left, 4 right
    IntPoint lastPosition;
    boolean triedToMoveForward = false;

    public DroidAlwaysRightMode(Labyrinth labyrinth, Droid d) {
        this.labyrinth = labyrinth;
        this.d = d;
        direction = 4;
        lastPosition = null;
    }

    public int getNextDir() {
        // starting point
        if (lastPosition == null) {
            lastPosition = d.getPosition().copy();
            return getDirectionFromCurrentDirection();
        }

        if (!triedToMoveForward && lastPosition.equals(d.getPosition())) {
            // we tried to move right but are on same space
            // go in direction
            triedToMoveForward = true;
            return direction;
        }

        if (triedToMoveForward && lastPosition.equals(d.getPosition())) {
            // we tried to go in direction but hit a wall
            triedToMoveForward = false;
            // if right and forward is walls, turn left
            if (isRightAndForwardWall()) {
                direction = rotateLeft();
            } else {
                // rotate right if we did not move
                direction = getDirectionFromCurrentDirection();
            }
            return direction;
        }

        if (!lastPosition.equals(d.getPosition())) {
            // we moved
            calculateDirection(); // set correct direction
            lastPosition = d.getPosition().copy();
            triedToMoveForward = false;
            return getDirectionFromCurrentDirection();
        }

        // rotate right if we did not move
        direction = getDirectionFromCurrentDirection();

        return direction;
    }

    private void calculateDirection() {
        if (lastPosition.y < d.getPosition().y) {
            direction = 2;
        } else if (lastPosition.y > d.getPosition().y) {
            direction = 1;
        } else if (lastPosition.x < d.getPosition().x) {
            direction = 4;
        } else {
            direction = 3;
        }
    }

    private int getDirectionFromCurrentDirection() {
        if (direction == 1) {
            return 4;
        }
        if (direction == 2) {
            return 3;
        }
        if (direction == 3) {
            return 1;
        }
        return 2;
    }

    private int rotateLeft() {
        if (direction == 1) {
            return 3;
        }
        if (direction == 2) {
            return 4;
        }
        if (direction == 3) {
            return 2;
        }
        return 1;
    }

    private boolean isRightAndForwardWall() {
        int[] around = buildAround();
        if (direction == 1) {
            return around[0] == 0 && around[3] == 0;
        }
        if (direction == 2) {
            return around[1] == 0 && around[2] == 0;
        }
        if (direction == 3) {
            return around[0] == 0 && around[2] == 0;
        }
        return around[1] == 0 && around[3] == 0;
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
}
