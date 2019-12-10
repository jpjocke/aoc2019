package org.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
                    asteroids.add(new Asteroid(j, i));
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
                if (i == j || !visible) {
                    continue;
                }
                AngleDistance compare = ads.get(j);
                if (o.getAngle() == compare.getAngle()) {
                    if (o.getDistance() > compare.getDistance()) {
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

    public static void printMap(int width, int height, List<Asteroid> asteroids) {
        printMap(width, height, asteroids, null);
    }

    public static void printMap(int width, int height, List<Asteroid> asteroids, Asteroid center) {
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                int finalY = y;
                int finalX = x;
                List<Asteroid> as = asteroids.stream()
                        .filter(a -> a.y == finalY)
                        .filter(a -> a.x == finalX)
                        .collect(toList());
                if (as.size() > 0) {
                    sb.append(as.get(0).isHit() ? "X" : " O");
                } else if(center != null && center.x == x && center.y == y) {
                    sb.append("P");
                }
                else {
                    sb.append(" ");
                }
            }
            System.out.println(sb);
        }
    }

    public static void printMapAngles(int width, int height, List<AngleDistance> asteroids, Asteroid center) {
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                int finalY = y;
                int finalX = x;
                List<AngleDistance> as = asteroids.stream()
                        .filter(a -> a.getAsteroid().y == finalY)
                        .filter(a -> a.getAsteroid().x == finalX)
                        .collect(toList());
                if (as.size() > 0) {
                    sb.append("(" + (int)as.get(0).getAngle() +")");
                } else if(center != null && center.x == x && center.y == y) {
                    sb.append("..P..");
                }
                else {
                    sb.append("   ");
                }
            }
            System.out.println(sb);
        }
    }
}
