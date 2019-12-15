package org.advent.util;

import java.util.Objects;

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

    public IntPoint copy() {
        return new IntPoint(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPoint intPoint = (IntPoint) o;
        return x == intPoint.x &&
                y == intPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return x + "," + y;
    }
}
