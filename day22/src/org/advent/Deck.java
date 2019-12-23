package org.advent;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;
import java.util.List;

public class Deck {
    int[] deck;

    public Deck(int size) {
        deck = new int[size];
        for (int i = 0; i < size; i++) {
            deck[i] = i;
        }
        System.out.println("Orig: " + Arrays.toString(deck));
    }

    public void runActions(List<Action> actions) {

        for (int i = 0; i < actions.size(); i++) {
            deck = actions.get(i).run(deck);
        }
    }

    public int[] getDeck() {
        return deck;
    }
}
