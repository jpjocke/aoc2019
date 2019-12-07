package org.advent;

import org.advent.util.Util;
import org.advent.util.code.Counter;
import org.advent.util.code.verifier.CodeVerifier;
import org.advent.util.code.verifier.MaxFourVerifier;
import org.advent.util.code.verifier.NoSameNumberVerifier;
import sun.awt.X11.XSystemTrayPeer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String in = "3,8,1001,8,10,8,105,1,0,0,21,46,63,76,97,118,199,280,361,442,99999,3,9,102,4,9,9,101,2,9,9,1002,9,5,9,101,4,9,9,102,2,9,9,4,9,99,3,9,101,5,9,9,102,3,9,9,101,3,9,9,4,9,99,3,9,1001,9,2,9,102,3,9,9,4,9,99,3,9,1002,9,5,9,101,4,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,5,9,101,3,9,9,1002,9,5,9,1001,9,5,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99";

    public static void main(String[] args) {
        int max = 0;

        int[] start = Util.toDigits(0, 5);
        int[] end = Util.toDigits(44444, 5);

        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new NoSameNumberVerifier());
        verifiers.add(new MaxFourVerifier());

        Counter c = new Counter(start, end, verifiers);
        while (!c.isDone()) {
            if (c.isCurrentNumberOk()) {
               int[] thrusterSeq = c.getCurrent();

               ThrusterSeq seq = new ThrusterSeq(thrusterSeq, in);
               int value = seq.runThrusters();
               if(value > max) {
                   max = value;
               }
            }
            c.nextNumber();
        }

        System.out.println("MAX: " + max);
    }
}
