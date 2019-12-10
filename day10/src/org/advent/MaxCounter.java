package org.advent;

import java.util.ArrayList;
import java.util.List;

public class MaxCounter {
    List<Asteroid> asteroids;

    Asteroid center;
    List<AngleDistance> ads;
    int maxCount;

    public MaxCounter(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public void calculateCenterAndMax() {
        int max = 0;

        for (int i = 0; i < asteroids.size(); i++) {
            List<AngleDistance> ads = new ArrayList<>();
            for(int j = 0; j < asteroids.size(); j++) {
                if(i == j) {
                    continue;
                }
                ads.add(new AngleDistance(asteroids.get(i), asteroids.get(j)));

            }
            int visible = Util.countVisible(ads);
            if (visible > max) {
                max = visible;
                center = asteroids.get(i);
                this.ads = ads;
            }
        }
        maxCount = max;
    }

    public Asteroid getCenter() {
        return center;
    }

    public List<AngleDistance> getAngleDistances() {
        return ads;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
