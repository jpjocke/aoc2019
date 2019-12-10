package org.advent;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class Verify {


    @Test
    public void map1() {
        String input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);

        asteroids.stream().forEach(a -> System.out.println(a));

        List<AngleDistance> ads = new ArrayList<>();
        for (int i = 1; i < asteroids.size(); i++) {
            ads.add(new AngleDistance(asteroids.get(0), asteroids.get(i)));
        }

        int visible = Util.countVisible(ads);

        ads.stream().forEach(a -> System.out.println(a));
        Assert.assertEquals(7, visible);
    }
    @Test
    public void map1_max() {
        String input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);
        MaxCounter mc = new MaxCounter(asteroids);
        mc.calculateCenterAndMax();
        Assert.assertEquals(8, mc.getMaxCount());
    }

    @Test
    public void map2_max() {
        String input = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);
        MaxCounter mc = new MaxCounter(asteroids);
        mc.calculateCenterAndMax();
        Assert.assertEquals(33, mc.getMaxCount());

        System.out.println(mc.getCenter());
        mc.getAngleDistances().stream().forEach(a -> System.out.println(a));
    }

    @Test
    public void map3_max() {
        String input = "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###.";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);
        MaxCounter mc = new MaxCounter(asteroids);
        mc.calculateCenterAndMax();
        Assert.assertEquals(35, mc.getMaxCount());
    }

    @Test
    public void map4_max() {
        String input = ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#..";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);
        MaxCounter mc = new MaxCounter(asteroids);
        mc.calculateCenterAndMax();
        Assert.assertEquals(41, mc.getMaxCount());
    }

    @Test
    public void map5_max() {
        String input = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);
        Util.printMap(20, 20, asteroids);
        MaxCounter mc = new MaxCounter(asteroids);
        mc.calculateCenterAndMax();
        Assert.assertEquals(210, mc.getMaxCount());

        Util.printMapAngles(20,20, mc.getAngleDistances(), mc.getCenter());

        Laser lzr = new Laser(mc.getCenter(), mc.getAngleDistances(), 179);
       List<Asteroid> hitList = lzr.getOrderedHitList();
       Assert.assertEquals(11, hitList.get(0).x);
        Assert.assertEquals(12, hitList.get(0).y);
        Assert.assertEquals(12, hitList.get(1).x);
        Assert.assertEquals(1, hitList.get(1).y);
    }

    @Test
    public void real() {
        String input = ".#..#..#..#...#..#...###....##.#....\n" +
                ".#.........#.#....#...........####.#\n" +
                "#..##.##.#....#...#.#....#..........\n" +
                "......###..#.#...............#.....#\n" +
                "......#......#....#..##....##.......\n" +
                "....................#..............#\n" +
                "..#....##...#.....#..#..........#..#\n" +
                "..#.#.....#..#..#..#.#....#.###.##.#\n" +
                ".........##.#..#.......#.........#..\n" +
                ".##..#..##....#.#...#.#.####.....#..\n" +
                ".##....#.#....#.......#......##....#\n" +
                "..#...#.#...##......#####..#......#.\n" +
                "##..#...#.....#...###..#..........#.\n" +
                "......##..#.##..#.....#.......##..#.\n" +
                "#..##..#..#.....#.#.####........#.#.\n" +
                "#......#..........###...#..#....##..\n" +
                ".......#...#....#.##.#..##......#...\n" +
                ".............##.......#.#.#..#...##.\n" +
                "..#..##...#...............#..#......\n" +
                "##....#...#.#....#..#.....##..##....\n" +
                ".#...##...........#..#..............\n" +
                ".............#....###...#.##....#.#.\n" +
                "#..#.#..#...#....#.....#............\n" +
                "....#.###....##....##...............\n" +
                "....#..........#..#..#.......#.#....\n" +
                "#..#....##.....#............#..#....\n" +
                "...##.............#...#.....#..###..\n" +
                "...#.......#........###.##..#..##.##\n" +
                ".#.##.#...##..#.#........#.....#....\n" +
                "#......#....#......#....###.#.....#.\n" +
                "......#.##......#...#.#.##.##...#...\n" +
                "..#...#.#........#....#...........#.\n" +
                "......#.##..#..#.....#......##..#...\n" +
                "..##.........#......#..##.#.#.......\n" +
                ".#....#..#....###..#....##..........\n" +
                "..............#....##...#.####...##.";

        char[][] map = Util.toMap(input);
        List<Asteroid> asteroids = Util.asteroids(map);
        MaxCounter mc = new MaxCounter(asteroids);
        mc.calculateCenterAndMax();
        Assert.assertEquals(276, mc.getMaxCount());
        System.out.println("mac: " + mc.getMaxCount());


        Laser lzr = new Laser(mc.getCenter(), mc.getAngleDistances(), 179);
        lzr.width = 37;
        lzr.height = 37;
        List<Asteroid> hitList = lzr.getOrderedHitList();
        System.out.println(hitList.get(199));
        // 3414 fel index 200
        // 3215 fel index 199
    }
}
