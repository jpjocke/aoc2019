package org.advent;

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
        dm.print(parser.getTeleports());

        Assert.assertEquals(2, 2);
    }
}
