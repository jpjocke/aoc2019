package org.advent.util.code;


import org.advent.util.code.verifier.CodeVerifier;
import java.util.List;

public class Counter {
    private int[] start;
    private int[] end;
    private List<CodeVerifier> verifiers;

    public Counter(int[] start, int[] end, List<CodeVerifier> verifiers) {
        this.start = start;
        this.end = end;

        this.verifiers = verifiers;
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

    public int[] getCurrent() {
        return start;
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
