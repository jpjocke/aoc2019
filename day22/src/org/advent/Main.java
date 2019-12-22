package org.advent;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

public class Main {
    private static String input = "deal with increment 53\n" +
            "cut -619\n" +
            "deal with increment 6\n" +
            "cut -2911\n" +
            "deal with increment 20\n" +
            "cut 6986\n" +
            "deal into new stack\n" +
            "deal with increment 38\n" +
            "cut -7609\n" +
            "deal with increment 55\n" +
            "cut -2390\n" +
            "deal into new stack\n" +
            "deal with increment 21\n" +
            "cut -349\n" +
            "deal into new stack\n" +
            "deal with increment 62\n" +
            "cut -9145\n" +
            "deal into new stack\n" +
            "cut 1013\n" +
            "deal with increment 63\n" +
            "cut -4214\n" +
            "deal with increment 6\n" +
            "cut -9471\n" +
            "deal into new stack\n" +
            "cut 1966\n" +
            "deal with increment 58\n" +
            "cut -4382\n" +
            "deal with increment 70\n" +
            "cut -6132\n" +
            "deal into new stack\n" +
            "deal with increment 25\n" +
            "cut -3962\n" +
            "deal with increment 6\n" +
            "cut 7401\n" +
            "deal with increment 72\n" +
            "cut -293\n" +
            "deal into new stack\n" +
            "cut 4528\n" +
            "deal with increment 64\n" +
            "cut 6899\n" +
            "deal with increment 49\n" +
            "cut 310\n" +
            "deal with increment 55\n" +
            "cut -6735\n" +
            "deal into new stack\n" +
            "deal with increment 31\n" +
            "cut 2368\n" +
            "deal with increment 48\n" +
            "cut -5602\n" +
            "deal with increment 23\n" +
            "cut 6410\n" +
            "deal with increment 72\n" +
            "cut 34\n" +
            "deal with increment 51\n" +
            "cut 2382\n" +
            "deal with increment 31\n" +
            "cut 2464\n" +
            "deal with increment 38\n" +
            "deal into new stack\n" +
            "deal with increment 18\n" +
            "cut 1764\n" +
            "deal with increment 57\n" +
            "deal into new stack\n" +
            "deal with increment 43\n" +
            "cut 8507\n" +
            "deal with increment 28\n" +
            "cut -3632\n" +
            "deal with increment 41\n" +
            "cut 8316\n" +
            "deal with increment 5\n" +
            "cut 610\n" +
            "deal with increment 74\n" +
            "cut -4956\n" +
            "deal with increment 45\n" +
            "cut 6518\n" +
            "deal with increment 60\n" +
            "cut 8750\n" +
            "deal with increment 6\n" +
            "cut -8411\n" +
            "deal with increment 14\n" +
            "cut -8300\n" +
            "deal with increment 29\n" +
            "cut -3297\n" +
            "deal with increment 49\n" +
            "cut -5261\n" +
            "deal with increment 30\n" +
            "cut -6595\n" +
            "deal into new stack\n" +
            "deal with increment 48\n" +
            "cut -8193\n" +
            "deal with increment 63\n" +
            "cut 6595\n" +
            "deal with increment 68\n" +
            "cut -9468\n" +
            "deal into new stack\n" +
            "cut -3051\n" +
            "deal with increment 3\n" +
            "cut -2249\n" +
            "deal with increment 9\n" +
            "cut -7233";

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();

        List<Action> actions = ActionParser.parse(input);
        Deck d = new Deck(10007);
        d.runActions(actions);

        int[] result = d.getDeck();
        // 2075 wrong -> was index 2019
        for(int i = 0; i < result.length; i++) {
            if(result[i] == 2019) {
                System.out.println("Card 2019 is at index: " + i);
            }
        }
        System.out.println("Position 2019: " + result[2019]);

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

}
