package org.advent;

public class AngleDistance {
    private final double angle;
    private final double distance;
    private final Asteroid asteroid;

    public AngleDistance(Asteroid a, Asteroid asteroid) {
        this.asteroid = asteroid;
        distance = hypotenusa(a, asteroid);
        double an;

        double endAngle;
        if (a.y < asteroid.y) {
            if (a.x < asteroid.x) {
                // 0 - 90
                // fixed?
                an = Math.asin(katet(asteroid.x, a.x) / distance);
                endAngle =  Math.toDegrees(an);
            } else {
                // 270 - 360
                // fixed?
                an = Math.asin(katet(asteroid.y, a.y) / distance);
                endAngle = 270 + Math.toDegrees(an);
            }
        } else {
            if (a.x < asteroid.x) {
                // 90 - 180
                // fixed?
                an = Math.asin(katet(a.y, asteroid.y) / distance);
                endAngle = 90 + Math.toDegrees(an);
            } else {
                // 180 - 270
                // fixed?
                an = Math.asin(katet(a.x, asteroid.x) / distance);
                endAngle = 180 + Math.toDegrees(an);
            }
        }
        angle =  Math.round(endAngle * 100.0) / 100.0;

    }

    private double hypotenusa(Asteroid a, Asteroid b) {
        double x = katet(a.x, b.x);
        double y = katet(a.y, b.y);
        return Math.sqrt(x * x + y * y);
    }

    private double katet(int a, int b) {
        return a - b;
    }

    public double getAngle() {
        return angle;
    }

    public double getDistance() {
        return distance;
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }

    @Override
    public String toString() {
        return "AngleDistance{" +
                "angle=" + angle +
                ", distance=" + distance +
                ", asteroid=" + asteroid +
                '}';
    }
}
