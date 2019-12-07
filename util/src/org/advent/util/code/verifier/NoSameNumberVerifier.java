package org.advent.util.code.verifier;

public class NoSameNumberVerifier implements CodeVerifier {
    @Override
    public boolean verifyCode(int[] code) {
        for (int i = 0; i < code.length - 1; i++) {
            for(int j = 0; j < code.length; j++) {
                if(i == j) {
                    continue;
                }
                if(code[i] == code[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
