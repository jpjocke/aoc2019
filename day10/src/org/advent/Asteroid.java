package org.advent;

public class Asteroid {
    int x;
    int y;
    private boolean hit = false;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public String toString() {
        return x + "," + y + ", hit: " + hit;
    }
}
