package org.advent;

import java.util.Arrays;

public class Action {
    private Type type;

    ;
    private int amount;

    public Action(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public int[] run(int[] deck) {
        int[] shuffled;
        switch (type) {
            case NEW_STACK:
                shuffled = newStack(deck);
                break;
            case CUT:
                shuffled = cut(deck);
                break;

            case DEAL_INCREMENT:
            default:
                shuffled = increment(deck);
                break;
        }
        return shuffled;
    }

    private int[] newStack(int[] deck) {
        int[] newStack = new int[deck.length];
        int shuffledIndex = 0;
        for (int i = deck.length - 1; i >= 0; i--) {
            newStack[shuffledIndex] = deck[i];
            shuffledIndex++;
        }

        return newStack;
    }

    private int[] cut(int[] deck) {
        int[] cutStack = new int[deck.length];
        if (amount > 0) {
            // take from bottom
            int[] cut = Arrays.copyOfRange(deck, 0, amount);
            for (int i = amount; i < deck.length; i++) {
                cutStack[i - amount] = deck[i];
            }
            for (int i = 0; i < cut.length; i++) {
                cutStack[deck.length - amount + i] = cut[i];
            }
        } else {
            // take from top
            int absolute = Math.abs(amount);
            int[] cut = Arrays.copyOfRange(deck, deck.length - absolute, deck.length);
            for (int i = 0; i < cut.length; i++) {
                cutStack[i] = cut[i];
            }
            for (int i = 0; i < deck.length - absolute; i++) {
                cutStack[absolute + i] = deck[i];
            }
        }

        // TODO
        return cutStack;
    }

    private int[] increment(int[] deck) {
        int[] increment = new int[deck.length];
        int deckPointer = 0;
        int shufflePointer = 0;
        while (deckPointer < deck.length) {
            increment[shufflePointer] = deck[deckPointer];
            shufflePointer += amount;
            shufflePointer = shufflePointer % deck.length;
            deckPointer++;
        }
        return increment;
    }

    public enum Type {NEW_STACK, CUT, DEAL_INCREMENT}
}
