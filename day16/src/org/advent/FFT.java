package org.advent;

import org.advent.util.Util;

import java.util.Arrays;

public class FFT {
    private static final byte[] BASE = new byte[]{0, 1, 0, -1};
    public static boolean DEBUG = false;
    byte[] codes;

    public FFT(byte[] start) {
        codes = start;
    }

    public void phase() {
     //   if (DEBUG) {
            System.out.println("Current: " + Arrays.toString(codes));
     //   }
        byte[] next = new byte[codes.length];
        for (int i = 0; i < next.length; i++) {
            next[i] = calculateAtIndex(i, codes);
        }
        if (DEBUG) {

            System.out.println("-----------------------");
            System.out.println("Result: " + Arrays.toString(next));
        }
        codes = next;
    }


    private byte calculateAtIndex(int index, byte[] array) {
        byte[] indexBase = null;
        boolean onlyAdd = false;
        if (index < array.length / 2) {
            indexBase = calculateBaseForIndex(index);
        } else {
            onlyAdd = true;
        }
        if (DEBUG) {
            //   System.out.println("-- Base: " + Arrays.toString(indexBase));
        }
        byte val = 0;


        if (DEBUG) {
            System.out.print("-- : ");
        }
        for (int i = index; i < array.length; i++) {

            if (DEBUG) {
                System.out.print(array[i] + "*" + (onlyAdd ? "ADD" : indexBase[i]) + " ");
            }
            if(onlyAdd) {
                val += array[i];
            } else {
                val += array[i] * indexBase[i];

            }
        }

        val = (byte) Math.abs(val);
        byte[] digits = Util.toDigitsByte(String.valueOf(val));

        val = digits[digits.length - 1];
        if (DEBUG) {
            System.out.println(" = " + val);
        }

        return val;
    }

    private byte[] calculateBaseForIndex(int index) {
        byte[] tmp = new byte[(index + 1) * BASE.length];
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

        return repeatBase(tmp);
    }

    private byte[] repeatBase(byte[] tmp) {
        byte[] repeated = new byte[codes.length];
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

    private void shiftOnce(byte[] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            if (i == tmp.length - 1) {
                tmp[tmp.length - 1] = 0;
                break;
            }
            tmp[i] = tmp[i + 1];
        }
    }

    public byte[] getCurrent() {
        return codes;
    }

}
