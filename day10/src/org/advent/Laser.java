package org.advent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Laser {

    Asteroid station;
    List<AngleDistance> ads;
    double angle;

    public int width = 20;
    public int height = 20;

    public Laser(Asteroid station, List<AngleDistance> ads, double startAngle) {
        this.station = station;
        this.ads = ads;
        angle = startAngle;
    }

    public List<Asteroid> getOrderedHitList() {
        List<Asteroid> hitList = new ArrayList<>();
        double nextAngle = angle;
        System.out.println(station);
        System.out.println("Shooting " + ads.size() + " asteroids");
        System.out.println("nextAngle: " + nextAngle);
        for (int i = 0; i < ads.size(); i++) {
            System.out.println("Shoot " + i + "/" + ads.size());
            nextAngle = nextAngle(nextAngle);
            System.out.println("nextAngle: " + nextAngle);
            AngleDistance closest = asteroidOnAngle(nextAngle);
            System.out.println("Shooting: " + closest);
            closest.getAsteroid().setHit(true);
            hitList.add(closest.getAsteroid());


           System.out.println("-------------------------------------------------------------------------------------");

            Util.printMap(width, height, ads.stream().map(ad -> ad.getAsteroid()).collect(Collectors.toList()), station);
        }
        return hitList;
    }

    private double nextAngle(double angle) {
        // my coordinates is wrong compansate by going counter-clockwise
        Optional<Double> next = ads.stream()
                .filter(ad -> !ad.getAsteroid().isHit())
                .filter(ad -> ad.getAngle() < angle)
                .map(ad -> ad.getAngle())
                .max(Comparator.comparingDouble(Double::valueOf));

        if (!next.isPresent()) {
            /*
            System.out.println(station);
            ads.stream()
                    .filter(ad -> !ad.getAsteroid().isHit())
                    .forEach(ad -> System.out.println("im left: " + ad));
*/
            Double next2 = ads.stream()
                    .filter(ad -> !ad.getAsteroid().isHit())
                    .filter(ad -> ad.getAngle() < 361)
                    .map(ad -> ad.getAngle())
                    .max(Comparator.comparingDouble(Double::valueOf))
                    .orElseThrow(() -> new RuntimeException("Found no more angles"));
            return next2;
        }
        return next.get();
    }

    private AngleDistance asteroidOnAngle(double angle) {
        AngleDistance asteroid = ads.stream()
                .filter(ad -> !ad.getAsteroid().isHit())
                .filter(ad -> ad.getAngle() == angle)
                .min((o1, o2) -> (int) (o1.getDistance() - o2.getDistance()))
                .orElseThrow(() -> new RuntimeException("No asteroids on angle"));
        return asteroid;
    }

}
