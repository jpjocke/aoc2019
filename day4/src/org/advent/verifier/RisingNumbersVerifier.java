package org.advent.verifier;

public class RisingNumbersVerifier implements CodeVerifier {
    @Override
    public boolean verifyCode(int[] code) {
        for (int i = 0; i < code.length - 1; i++) {
            if(code[i + 1] < code[i]) {
                return false;
            }
        }
        return true;
    }
}
