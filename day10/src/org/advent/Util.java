package org.advent;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static char[][] toMap(String raw) {
        String[] split = raw.split("\n");
        char[][] map = new char[split.length][split[0].length()];

        for (int i = 0; i < split.length; i++) {
            char[] line = split[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j];
            }
        }

        return map;
    }

    public static List<Asteroid> asteroids(char[][] map) {
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') {
                    asteroids.add(new Asteroid(i, j));
                }
            }
        }
        return asteroids;
    }

    public static int countVisible(List<AngleDistance> ads) {
        int count = 0;
        for (int i = 0; i < ads.size(); i++) {
            AngleDistance o = ads.get(i);
            boolean visible = true;
            for (int j = 0; j < ads.size(); j++) {
                if(i == j || !visible) {
                    continue;
                }
                AngleDistance compare = ads.get(j);
                if (o.getAngle() == compare.getAngle()){
                    if ( o.getDistance() > compare.getDistance()){
                        visible = false;
                        break;
                    }
                }
            }
            if (visible) {
                count++;
            }
        }

        return count;
    }
}
