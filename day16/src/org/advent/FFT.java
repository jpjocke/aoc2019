package org.advent;

import org.advent.util.Util;

import java.util.Arrays;

public class FFT {
    int[] start;
    int phase;
    int[] current;
    int[] base;

    public FFT(int[] start) {
        this.start = start;
        base = new int[]{0, 1, 0, -1};
        current = start;
    }

    public void phase() {
        System.out.println("Current: " + Arrays.toString(current));
        int[] next = new int[start.length];
        for (int i = 0; i < next.length; i++) {
            next[i] = calculateAtIndex(i, current);
            System.out.println("-----------------------");
        }
        System.out.println("Result: " + Arrays.toString(next));
        current = next;
    }

    private int calculateAtIndex(int index, int[] array) {
        int[] indexBase = calculateBaseForIndex(index);
        System.out.println("-- Base: " + Arrays.toString(indexBase));
        int val = 0;

        System.out.println();

        System.out.print("-- : ");
        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i] + "*" + indexBase[i] + " ");
            val += array[i] * indexBase[i];
        }

        val = Math.abs(val);
        int[] digits = Util.toDigits(val);

        val = digits[digits.length - 1];
        System.out.println(" = " + val);

        return val;
    }

    // done
    private int[] calculateBaseForIndex(int index) {
        int[] tmp = new int[(index + 1) * base.length];
        int bIndex = 0;
        int omgPointer = 0;
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < index + 1; j++) {
                tmp[omgPointer] = base[bIndex];
                omgPointer++;
            }
            bIndex++;
        }

        shiftOnce(tmp);

        return repeat(tmp);
    }

    private int[] repeat(int[] tmp) {
        int[] repeated = new int[start.length];
        int index = 0;
        for (int i = 0; i < repeated.length; i++) {
            repeated[i] = tmp[index];
            index++;
            if (index == tmp.length) {
                index = 0;
            }
        }
        return repeated;
    }

    private void shiftOnce(int[] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            if (i == tmp.length - 1) {
                tmp[tmp.length - 1] = 0;
                break;
            }
            tmp[i] = tmp[i + 1];
        }
    }

    public int[] getCurrent() {
        return current;
    }

}
