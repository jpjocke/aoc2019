package org.advent.util;

public class IntVector {
    public int x;
    public int y;
    public int z;

    public IntVector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y +
                ", z=" + z;
    }
}
