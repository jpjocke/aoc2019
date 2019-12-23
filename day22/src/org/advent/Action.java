package org.advent;

import java.util.Arrays;

public class Action {
    private Type type;

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

        System.out.println(this + ". NewStack : " + Arrays.toString(newStack));
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
        System.out.println(this + ". Cut : " + Arrays.toString(cutStack));
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
        System.out.println(this + ". Increment : " + Arrays.toString(increment));
        return increment;
    }

    public long reverseIndex(long index, long size) {
        switch (type) {
            case NEW_STACK:
                return reverseIndexStack(index, size);
            case CUT:
                return reverseCut(index, size);

            case DEAL_INCREMENT:
            default:
                return reverseIncrement(index, size);
        }
    }

    private long reverseIndexStack(long index, long size) {
        long result = (size - 1) - index;
    //    System.out.println(this + ". ReverseStack " + index + " in size " + size + " = " + result);
        return result;
    }

    private long reverseCut(long index, long size) {
        long result;
        if (amount > 0) {
            if (index >= size - amount) {
                result = index - (size - amount);
            } else {
                result = index + amount;
            }
        } else {
            int absolute = Math.abs(amount);
            if (index < absolute) {
                result = index + (size - absolute);
            } else {
                result = index - absolute;
            }
        }
    //    System.out.println(this + ". ReverseCut " + index + " in size " + size + " = " + result);
        return result;
    }

    private long reverseIncrement(long index, long size) {

        long count = 0;
        long deckPointer = 0;
        while (deckPointer != index) {
        //    System.out.println("reverseIncrement " + deckPointer + ", " + index + ", " + count);
            if (deckPointer < index) {
                // hitta multiplier till index
                long multiplier = (index - deckPointer) / amount;
                count += multiplier;
                deckPointer += multiplier * amount;
        //        System.out.println("A (" + index + " - " + deckPointer + ") / " + amount + " = " + multiplier);
                if (deckPointer == index) {
                    break;
                }
            } else {
                // hitta multiplier till size
                long multiplier = (size - deckPointer) / amount;
                count += multiplier;
                deckPointer += multiplier * amount;
        //        System.out.println("B (" + size + " - " + deckPointer + ") / " + amount + " = " + multiplier);


            }
            deckPointer += amount;
        //    System.out.println("C " + deckPointer);
            deckPointer = deckPointer % size;
            count++;

        }

    //    System.out.println(this + ". ReverseIncrement " + index + " in size " + size + " = " + count);
        return count;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }

    public enum Type {NEW_STACK, CUT, DEAL_INCREMENT}
}
