package org.advent.util;

public class IntPoint {
    public int x;
    public int y;

    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
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
