package org.advent;

public class IntPoint {
    int x;
    int y;

    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void runCommand(Command command) {
        switch (command.getDirection()) {
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
        }
    }

    public int manhattanDistance(IntPoint other) {
        int dist = 0;
        if (x > other.x) {
            dist += x - other.x;
        } else {
            dist += other.x - x;
        }
        if (y > other.y) {
            dist += y - other.y;
        } else {
            dist += other.y - y;
        }
        return dist;
    }

    public String toString() {
        return x + "," + y;
    }
}
