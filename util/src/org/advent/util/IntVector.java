package org.advent.util;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntVector intVector = (IntVector) o;
        return x == intVector.x &&
                y == intVector.y &&
                z == intVector.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
