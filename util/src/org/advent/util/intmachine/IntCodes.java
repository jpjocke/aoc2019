package org.advent.util.intmachine;

import org.advent.util.Util;

import java.util.Arrays;
import java.util.List;

public class IntCodes {
    private long[] codes;
    private int relativeBase;

    public IntCodes(String raw) {
        List<Long> ops = Util.toOperations(raw);
        codes = new long[ops.size()];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = ops.get(i);
        }
        relativeBase = 0;
    }

    public long get(int index) {
        if (index >= codes.length) {
            return 0;
        }
        return codes[index];
    }

    public void setValueAtIndex(long index, long value) {
        int iIndex = (int) index;
        if (index >= codes.length) {
            long[] oldCodes = codes;
            codes = new long[iIndex + 1];
            for (int i = 0; i < codes.length; i++) {
                if (i < oldCodes.length) {
                    codes[i] = oldCodes[i];
                } else {
                    codes[i] = 0;
                }
            }
        }
        codes[iIndex] = value;
    }

    public int getRelativeBase() {
        return relativeBase;
    }

    public void setRelativeBase(int relativeBase) {
        this.relativeBase = relativeBase;
    }

    @Override
    public String toString() {
        return "IntCodes{" +
                "codes=" + Arrays.toString(codes) +
                '}';
    }
}
