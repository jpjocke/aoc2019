package org.advent;

import org.advent.dfs.DfsCalcDonut;
import org.advent.dfsstep2.dfs.DfsCalcDonut2;
import org.advent.dfsstep2.dfs.DonutMazeStep2;
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

    @Test
    public void map1step2() {
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

        DonutMazeStep2 dm = new DonutMazeStep2(parser.getMap());
        //   dm.print(parser.getTeleports());

        DfsCalcDonut2 calc = new DfsCalcDonut2(parser.getTeleports(), dm, 3);
        int steps = calc.exploreFromStart();

        // 26 now
        Assert.assertEquals(26, steps);
    }

    @Test
    public void map3step2() {
        String input = "             Z L X W       C                 \n" +
                "             Z P Q B       K                 \n" +
                "  ###########.#.#.#.#######.###############  \n" +
                "  #...#.......#.#.......#.#.......#.#.#...#  \n" +
                "  ###.#.#.#.#.#.#.#.###.#.#.#######.#.#.###  \n" +
                "  #.#...#.#.#...#.#.#...#...#...#.#.......#  \n" +
                "  #.###.#######.###.###.#.###.###.#.#######  \n" +
                "  #...#.......#.#...#...#.............#...#  \n" +
                "  #.#########.#######.#.#######.#######.###  \n" +
                "  #...#.#    F       R I       Z    #.#.#.#  \n" +
                "  #.###.#    D       E C       H    #.#.#.#  \n" +
                "  #.#...#                           #...#.#  \n" +
                "  #.###.#                           #.###.#  \n" +
                "  #.#....OA                       WB..#.#..ZH\n" +
                "  #.###.#                           #.#.#.#  \n" +
                "CJ......#                           #.....#  \n" +
                "  #######                           #######  \n" +
                "  #.#....CK                         #......IC\n" +
                "  #.###.#                           #.###.#  \n" +
                "  #.....#                           #...#.#  \n" +
                "  ###.###                           #.#.#.#  \n" +
                "XF....#.#                         RF..#.#.#  \n" +
                "  #####.#                           #######  \n" +
                "  #......CJ                       NM..#...#  \n" +
                "  ###.#.#                           #.###.#  \n" +
                "RE....#.#                           #......RF\n" +
                "  ###.###        X   X       L      #.#.#.#  \n" +
                "  #.....#        F   Q       P      #.#.#.#  \n" +
                "  ###.###########.###.#######.#########.###  \n" +
                "  #.....#...#.....#.......#...#.....#.#...#  \n" +
                "  #####.#.###.#######.#######.###.###.#.#.#  \n" +
                "  #.......#.......#.#.#.#.#...#...#...#.#.#  \n" +
                "  #####.###.#####.#.#.#.#.###.###.#.###.###  \n" +
                "  #.......#.....#.#...#...............#...#  \n" +
                "  #############.#.#.###.###################  \n" +
                "               A O F   N                     \n" +
                "               A A D   M                     ";

        DonutMazeParser parser = new DonutMazeParser(input);

        DonutMazeStep2 dm = new DonutMazeStep2(parser.getMap());
        //   dm.print(parser.getTeleports());

        DfsCalcDonut2 calc = new DfsCalcDonut2(parser.getTeleports(), dm, 12);
        int steps = calc.exploreFromStart();

        // 26 now
        Assert.assertEquals(396, steps);
    }
}
