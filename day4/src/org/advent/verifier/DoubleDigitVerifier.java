package org.advent.verifier;

public class DoubleDigitVerifier implements CodeVerifier {
    @Override
    public boolean verifyCode(int[] code) {
        for (int i = 0; i < code.length - 1; i++) {
            if (code[i] == code[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
