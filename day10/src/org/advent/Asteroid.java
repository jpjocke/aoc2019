package org.advent;

public class Asteroid {
    int x;
    int y;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + "," + y;
    }
}
