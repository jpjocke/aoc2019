package org.advent;

public class AngleDistance {
    private final double angle;
    private final double distance;

    public AngleDistance(Asteroid a, Asteroid b) {
        double katetA = katet(a.x, b.x);
        double katetB = katet(a.y, b.y);
        distance = hypotenusa(a, b);
        double an;
       // if (a.x > b.x) {
            an = Math.acos(katetA / distance);
      //  } else {
       //     an = Math.asin(katetB / distance);
      //  }

        int test = 0;
        if(a.y < b.y) {
            if(a.x < b.x) {
                test = 180;
            } else {
                test = -90;
            }
        } else {
            if(a.x < b.x) {
                test = 90;
            } else {
                test = 0;
            }
        }
        angle = (Math.toDegrees(Math.round(an * 10000.0) / 10000.0)) + test;

    }

    private double hypotenusa(Asteroid a, Asteroid b) {
        double x = katet(a.x, b.x);
        double y = katet(a.y, b.y);
        return Math.sqrt(x * x + y * y);
    }

    private double katet(int a, int b) {
        double x;
      //  if (a > b) {
            x = a - b;
     //   } else {
        //    x = b - a;
      //  }
        return x;
    }

    public double getAngle() {
        return angle;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "AngleDistance{" +
                "angle=" + angle +
                ", distance=" + distance +
                '}';
    }
}
