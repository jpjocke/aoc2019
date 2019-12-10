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
        Assert.assertEquals(8, mc.findMaxVisible());
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
        Assert.assertEquals(33, mc.findMaxVisible());
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
        Assert.assertEquals(35, mc.findMaxVisible());
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
        Assert.assertEquals(41, mc.findMaxVisible());
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
        MaxCounter mc = new MaxCounter(asteroids);
        Assert.assertEquals(210, mc.findMaxVisible());
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
        int max = mc.findMaxVisible();
        System.out.println("mac: " + max);
        // 251 fel too low
        Assert.assertEquals(276, max);
    }
}
