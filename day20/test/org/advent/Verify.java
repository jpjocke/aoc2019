package org.advent;

import org.advent.dfs.DfsCalcDonut;
import org.junit.Assert;
import org.junit.Test;



public class Verify {


    @Test
    public void map1() {
        String input = "         A           \n" +
                "         A           \n" +
                "  #######.#########  \n" +
                "  #######.........#  \n" +
                "  #######.#######.#  \n" +
                "  #######.#######.#  \n" +
                "  #######.#######.#  \n" +
                "  #####  B    ###.#  \n" +
                "BC...##  C    ###.#  \n" +
                "  ##.##       ###.#  \n" +
                "  ##...DE  F  ###.#  \n" +
                "  #####    G  ###.#  \n" +
                "  #########.#####.#  \n" +
                "DE..#######...###.#  \n" +
                "  #.#########.###.#  \n" +
                "FG..#########.....#  \n" +
                "  ###########.#####  \n" +
                "             Z       \n" +
                "             Z       ";

        DonutMazeParser parser = new DonutMazeParser(input);

        DonutMaze dm = new DonutMaze(parser.getMap());
     //   dm.print(parser.getTeleports());

        DfsCalcDonut calc = new DfsCalcDonut(parser.getTeleports(), dm);
        int steps = calc.exploreFromStart();

        Assert.assertEquals(23, steps);
    }

    @Test
    public void map2() {
        String input = "                   A               \n" +
                "                   A               \n" +
                "  #################.#############  \n" +
                "  #.#...#...................#.#.#  \n" +
                "  #.#.#.###.###.###.#########.#.#  \n" +
                "  #.#.#.......#...#.....#.#.#...#  \n" +
                "  #.#########.###.#####.#.#.###.#  \n" +
                "  #.............#.#.....#.......#  \n" +
                "  ###.###########.###.#####.#.#.#  \n" +
                "  #.....#        A   C    #.#.#.#  \n" +
                "  #######        S   P    #####.#  \n" +
                "  #.#...#                 #......VT\n" +
                "  #.#.#.#                 #.#####  \n" +
                "  #...#.#               YN....#.#  \n" +
                "  #.###.#                 #####.#  \n" +
                "DI....#.#                 #.....#  \n" +
                "  #####.#                 #.###.#  \n" +
                "ZZ......#               QG....#..AS\n" +
                "  ###.###                 #######  \n" +
                "JO..#.#.#                 #.....#  \n" +
                "  #.#.#.#                 ###.#.#  \n" +
                "  #...#..DI             BU....#..LF\n" +
                "  #####.#                 #.#####  \n" +
                "YN......#               VT..#....QG\n" +
                "  #.###.#                 #.###.#  \n" +
                "  #.#...#                 #.....#  \n" +
                "  ###.###    J L     J    #.#.###  \n" +
                "  #.....#    O F     P    #.#...#  \n" +
                "  #.###.#####.#.#####.#####.###.#  \n" +
                "  #...#.#.#...#.....#.....#.#...#  \n" +
                "  #.#####.###.###.#.#.#########.#  \n" +
                "  #...#.#.....#...#.#.#.#.....#.#  \n" +
                "  #.###.#####.###.###.#.#.#######  \n" +
                "  #.#.........#...#.............#  \n" +
                "  #########.###.###.#############  \n" +
                "           B   J   C               \n" +
                "           U   P   P               ";

        DonutMazeParser parser = new DonutMazeParser(input);

        DonutMaze dm = new DonutMaze(parser.getMap());
       // dm.print(parser.getTeleports());

        DfsCalcDonut calc = new DfsCalcDonut(parser.getTeleports(), dm);
        int steps = calc.exploreFromStart();

        Assert.assertEquals(58, steps);
    }
}
