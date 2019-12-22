package org.advent;

import java.util.Arrays;
import java.util.List;

public class Deck {
    int[] deck;

    public Deck(int size) {
        deck = new int[size];
        for (int i = 0; i < size; i++) {
            deck[i] = i;
        }
    }

    public void runActions(List<Action> actions) {

        for(int i = 0; i < actions.size(); i++) {
            deck = actions.get(i).run(deck);
            System.out.println(Arrays.toString(deck));
        }
    }

    public int[] getDeck() {
        return deck;
    }
}
