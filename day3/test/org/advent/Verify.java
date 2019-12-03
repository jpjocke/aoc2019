package org.advent;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Verify {

    @Test
    public void test1() {
        String w1List = "R8,U5,L5,D3";
        String w2List = "U7,R6,D4,L4";
        int expectedDistance = 6;

        List<Command> w1Commands = CommandParser.parseList(w1List);
        List<Command> w2Commands = CommandParser.parseList(w2List);

        Board board = new Board(20, 2);

        w1Commands.stream()
                .forEach(command -> board.runLength(command, 0));
        w2Commands.stream()
                .forEach(command -> board.runLength(command, 1));

        board.print();

        List<IntPoint> intersections = board.getIntersections();
        IntPoint start = board.getStart();
        intersections.stream().forEach(point -> {
            System.out.println(point);
            System.out.println(point.manhattanDistance(start));
        });

        int smallest = ManhattanComparer.getSmallest(intersections, start);
        Assert.assertEquals(expectedDistance, smallest);
    }


    @Test
    public void test2() {
        String w1List = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
        String w2List = "U62,R66,U55,R34,D71,R55,D58,R83";
        int expectedDistance = 159;

        List<Command> w1Commands = CommandParser.parseList(w1List);
        List<Command> w2Commands = CommandParser.parseList(w2List);

        Board board = new Board(2000, 2);

        w1Commands.stream()
                .forEach(command -> board.runLength(command, 0));
        w2Commands.stream()
                .forEach(command -> board.runLength(command, 1));

        List<IntPoint> intersections = board.getIntersections();
        IntPoint start = board.getStart();
        intersections.stream().forEach(point -> {
            System.out.println(point);
            System.out.println(point.manhattanDistance(start));
        });

        int smallest = ManhattanComparer.getSmallest(intersections, start);
        Assert.assertEquals(expectedDistance, smallest);
    }

    @Test
    public void test3() {
        String w1List = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
        String w2List = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        int expectedDistance = 135;

        List<Command> w1Commands = CommandParser.parseList(w1List);
        List<Command> w2Commands = CommandParser.parseList(w2List);

        Board board = new Board(2000, 2);

        w1Commands.stream()
                .forEach(command -> board.runLength(command, 0));
        w2Commands.stream()
                .forEach(command -> board.runLength(command, 1));

        List<IntPoint> intersections = board.getIntersections();
        IntPoint start = board.getStart();
        intersections.stream().forEach(point -> {
            System.out.println(point);
            System.out.println(point.manhattanDistance(start));
        });

        int smallest = ManhattanComparer.getSmallest(intersections, start);
        Assert.assertEquals(expectedDistance, smallest);
    }

    @Test
    public void bitwiseTest() {
        int q = 0;
        int w = 1;
        byte a1 = (byte)(1);
        byte a2 = (byte)(2);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a1 | a2 | a1);
        byte a = 1;
        byte b = 10;
        System.out.println(a & a);
        System.out.println(a | a);
        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(b & b);
        System.out.println(b | b);
    }
}
