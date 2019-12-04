package org.advent;

import org.advent.verifier.CodeVerifier;
import org.advent.verifier.DoubleDigitVerifier;
import org.advent.verifier.OnlyDoubleDigitVerifier;
import org.advent.verifier.RisingNumbersVerifier;

import java.util.ArrayList;
import java.util.List;

public class Counter {
    private int[] start;
    private int[] end;
    private List<CodeVerifier> verifiers;

    public Counter(int[] start, int[] end) {
        this.start = start;
        this.end = end;

        verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());
    }

    public boolean isDone() {
        for (int i = 0; i < start.length; i++) {
            if (start[i] != end[i]) {
                return false;
            }
        }
        return true;
    }

    public void nextNumber() {
        start[start.length - 1]++;
        for (int i = start.length - 1; i >= 1; i--) {
            if(start[i] == 10) {
                start[i] = 0;
                start[i - 1]++;
            }
        }
    }

    public boolean isCurrentNumberOk() {
        return verifiers.stream().allMatch(codeVerifier -> codeVerifier.verifyCode(start));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("now: ");
        for(int i = 0; i < start.length; i++) {
            sb.append(start[i] + ",");
        }
        sb.append(" End: ");
        for(int i = 0; i < end.length; i++) {
            sb.append(end[i] + ",");
        }
        return sb.toString();
    }
}
