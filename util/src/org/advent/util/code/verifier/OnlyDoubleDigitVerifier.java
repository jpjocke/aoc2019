package org.advent.util.code.verifier;

public class OnlyDoubleDigitVerifier implements CodeVerifier {
    @Override
    public boolean verifyCode(int[] code) {
        // 111122 OK because 22 is ok
        // 123444 not OK 444 is more than 2
        boolean ok = false;
        for (int i = 0; i < code.length - 1; i++) {
            if (isIndexAnOkPair(code, i)) {
                ok = true;
            }
        }
        return ok;
    }

    private boolean isIndexAnOkPair(int[] code, int i) throws IndexOutOfBoundsException {
        if (code[i] == code[i + 1]) {
            if (i > 0 && code[i] == code[i -1]) {
                return false;
            }
            if (i <= code.length - 3 && code[i + 1] == code[i + 2]) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isOneNeighbourOk(int[] code, int i) {
        return code[i] == code[i - 1] || code[i] == code[i + 1];
    }

    private boolean isBothNeighbourSame(int[] code, int i) {
        return code[i] == code[i - 1] && code[i] == code[i + 1];
    }
}
