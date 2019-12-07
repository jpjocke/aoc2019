package org.advent.util.code.verifier;

public class MaxFourVerifier implements CodeVerifier {
    @Override
    public boolean verifyCode(int[] code) {
        for (int i = 0; i < code.length; i++) {
            if (code[i] > 4) {
                return false;
            }
        }
        return true;
    }
}
