package org.advent;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class Verify {


    @Test
    public void deck1() {
        String input = "deal with increment 7\n" +
                "deal into new stack\n" +
                "deal into new stack";

        List<Action> actions = ActionParser.parse(input);
        Deck d = new Deck(10);
        d.runActions(actions);

        int[] result = d.getDeck();

        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(3, result[1]);
        Assert.assertEquals(6, result[2]);
        Assert.assertEquals(9, result[3]);
        Assert.assertEquals(2, result[4]);
        Assert.assertEquals(5, result[5]);
        Assert.assertEquals(8, result[6]);
        Assert.assertEquals(1, result[7]);
        Assert.assertEquals(4, result[8]);
        Assert.assertEquals(7, result[9]);
    }

    @Test
    public void reverseDeck1() {
        String input = "deal with increment 7\n" +
                "deal into new stack\n" +
                "deal into new stack";

        List<Action> actions = ActionParser.parse(input);
        long result = 6;
        for (int i = actions.size() - 1; i >= 0; i--) {
            result = actions.get(i).reverseIndex(result, 10);
        }

        Assert.assertEquals(8, result);
    }

    @Test
    public void deck2() {
        String input = "cut 6\n" +
                "deal with increment 7\n" +
                "deal into new stack";

        List<Action> actions = ActionParser.parse(input);
        Deck d = new Deck(10);
        d.runActions(actions);

        int[] result = d.getDeck();

        Assert.assertEquals(3, result[0]);
        Assert.assertEquals(0, result[1]);
        Assert.assertEquals(7, result[2]);
        Assert.assertEquals(4, result[3]);
        Assert.assertEquals(1, result[4]);
        Assert.assertEquals(8, result[5]);
        Assert.assertEquals(5, result[6]);
        Assert.assertEquals(2, result[7]);
        Assert.assertEquals(9, result[8]);
        Assert.assertEquals(6, result[9]);
    }

    @Test
    public void reverseDeck2() {
        String input = "cut 6\n" +
                "deal with increment 7\n" +
                "deal into new stack";

        List<Action> actions = ActionParser.parse(input);
        long result = 2;
        for (int i = actions.size() - 1; i >= 0; i--) {
            result = actions.get(i).reverseIndex(result, 10);
        }

        Assert.assertEquals(7, result);
    }

    @Test
    public void deck3() {
        String input = "deal with increment 7\n" +
                "deal with increment 9\n" +
                "cut -2";

        List<Action> actions = ActionParser.parse(input);
        Deck d = new Deck(10);
        d.runActions(actions);

        int[] result = d.getDeck();

        Assert.assertEquals(6, result[0]);
        Assert.assertEquals(3, result[1]);
        Assert.assertEquals(0, result[2]);
        Assert.assertEquals(7, result[3]);
        Assert.assertEquals(4, result[4]);
        Assert.assertEquals(1, result[5]);
        Assert.assertEquals(8, result[6]);
        Assert.assertEquals(5, result[7]);
        Assert.assertEquals(2, result[8]);
        Assert.assertEquals(9, result[9]);
    }

    @Test
    public void reverseDeck3() {
        String input = "deal with increment 7\n" +
                "deal with increment 9\n" +
                "cut -2";

        List<Action> actions = ActionParser.parse(input);
        long result = 5;
        for (int i = actions.size() - 1; i >= 0; i--) {
            result = actions.get(i).reverseIndex(result, 10);
        }

        Assert.assertEquals(1, result);
    }

    @Test
    public void deck4() {
        String input = "deal into new stack\n" +
                "cut -2\n" +
                "deal with increment 7\n" +
                "cut 8\n" +
                "cut -4\n" +
                "deal with increment 7\n" +
                "cut 3\n" +
                "deal with increment 9\n" +
                "deal with increment 3\n" +
                "cut -1";

        List<Action> actions = ActionParser.parse(input);
        Deck d = new Deck(10);
        d.runActions(actions);

        int[] result = d.getDeck();

        Assert.assertEquals(9, result[0]);
        Assert.assertEquals(2, result[1]);
        Assert.assertEquals(5, result[2]);
        Assert.assertEquals(8, result[3]);
        Assert.assertEquals(1, result[4]);
        Assert.assertEquals(4, result[5]);
        Assert.assertEquals(7, result[6]);
        Assert.assertEquals(0, result[7]);
        Assert.assertEquals(3, result[8]);
        Assert.assertEquals(6, result[9]);
    }

    @Test
    public void reverseDeck4() {
        String input = "deal into new stack\n" +
                "cut -2\n" +
                "deal with increment 7\n" +
                "cut 8\n" +
                "cut -4\n" +
                "deal with increment 7\n" +
                "cut 3\n" +
                "deal with increment 9\n" +
                "deal with increment 3\n" +
                "cut -1";

        List<Action> actions = ActionParser.parse(input);

        long result = 0;
        long expected = 9;
        for (int i = actions.size() - 1; i >= 0; i--) {
            result = actions.get(i).reverseIndex(result, 10);
        }
        Assert.assertEquals(expected, result);
/*
        result = 1;
        expected = 2;
        for (int i = actions.size() - 1; i >= 0; i--) {
            result = actions.get(i).reverseIndex(result, 10);
        }
        Assert.assertEquals(expected, result);

 */
    }
}
