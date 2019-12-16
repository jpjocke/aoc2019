package org.advent;

import java.util.Arrays;

public class FFTOpt {
    public static boolean DEBUG = false;
    byte[] codes;

    public FFTOpt(byte[] start, int offset) {
        codes = new byte[start.length - offset];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = start[offset + i];
        }
    }

    public void phase() {
        if (DEBUG) {
            System.out.println("Current: " + Arrays.toString(codes));
        }
        byte[] next = new byte[codes.length];
        next[next.length - 1] = codes[next.length - 1];
        for (int i = next.length - 2; i >= 0; i--) {
            next[i] = (byte) ((codes[i] + next[i+1]) % 10);
        }
        if (DEBUG) {

            System.out.println("-----------------------");
            System.out.println("Result: " + Arrays.toString(next));
        }
        codes = next;
    }

    public byte[] getCurrent() {
        return codes;
    }

}
