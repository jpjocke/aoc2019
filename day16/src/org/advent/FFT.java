package org.advent;

import org.advent.util.Util;

import java.util.Arrays;

public class FFT {
    private static final int[] BASE = new int[]{0, 1, 0, -1};
    public static boolean DEBUG = false;
    int[] start;
    int[] current;
    long msgOffset;

    public FFT(int[] start, long offset) {
        // start ska vara (start) repeterat 10000 gånger eller offset + 8
        this.start = start;
        current = start;
        this.msgOffset = offset;
    }

    public static long getMsgOffset(int[] input) {
        String offset = "";
        for (int i = 0; i < 7; i++) {
            offset = offset + input[i];
        }

        return Long.parseLong(offset);
    }
/*
    private int[] repeatToFindOffset() {
        long actualPointer = 0;
        outerloop:
        for (int i = 0; i < 10000; i++) {

            if (actualPointer > msgOffset + 8) {
                break outerloop;
            }
        }
    }
*/
    public void phase() {
        if (DEBUG) {
            System.out.println("Current: " + Arrays.toString(current));
        }
        int[] next = new int[start.length];
        for (int i = 0; i < next.length; i++) {
            next[i] = calculateAtIndex(i, current);
            if (DEBUG) {
                System.out.println("-----------------------");
            }
        }
        if (DEBUG) {

            System.out.println("Result: " + Arrays.toString(next));
        }
        current = next;
    }

    public int[] getMessageFromOffset() {
        long start = msgOffset % current.length;
        int[] msg = new int[8];
        for (int i = 0; i < msg.length; i++) {
            msg[i] = current[(int) start];
            start++;
            if (start >= current.length) {
                start = 0;
            }
        }
        return msg;
    }

    private int calculateAtIndex(int index, int[] array) {
        int[] indexBase = calculateBaseForIndex(index);
        if (DEBUG) {
            System.out.println("-- Base: " + Arrays.toString(indexBase));
        }
        int val = 0;


        if (DEBUG) {
            System.out.print("-- : ");
        }
        for (int i = 0; i < array.length; i++) {

            if (DEBUG) {
                System.out.print(array[i] + "*" + indexBase[i] + " ");
            }
            val += array[i] * indexBase[i];
        }

        val = Math.abs(val);
        int[] digits = Util.toDigits(val);

        val = digits[digits.length - 1];
        if (DEBUG) {
            System.out.println(" = " + val);
        }

        return val;
    }

    private int[] calculateBaseForIndex(int index) {
        int[] tmp = new int[(index + 1) * BASE.length];
        int bIndex = 0;
        int omgPointer = 0;
        for (int i = 0; i < BASE.length; i++) {
            for (int j = 0; j < index + 1; j++) {
                tmp[omgPointer] = BASE[bIndex];
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
