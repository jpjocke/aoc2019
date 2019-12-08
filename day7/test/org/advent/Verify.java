package org.advent;

import org.advent.util.Util;
import org.junit.Assert;
import org.junit.Test;

public class Verify {

    @Test
    public void maxThruster1() {
        int phase = 43210;
        String input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        int[] phaseInput = Util.toDigits(phase);
        int expected = 43210;

        ThrusterSeq ts = new ThrusterSeq(phaseInput, input);
        int value = ts.runThrusters();

        Assert.assertEquals(expected, value);
    }

    @Test
    public void maxThruster2() {
        int phase = 1234;
        String input = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        int[] phaseInput = Util.toDigits(phase, 5);
        int expected = 54321;

        ThrusterSeq ts = new ThrusterSeq(phaseInput, input);
        int value = ts.runThrusters();

        Assert.assertEquals(expected, value);
    }

    @Test
    public void maxThruster3() {
        int phase = 10432;
        String input = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0";
        int[] phaseInput = Util.toDigits(phase, 5);
        int expected = 65210;

        ThrusterSeq ts = new ThrusterSeq(phaseInput, input);
        int value = ts.runThrusters();

        Assert.assertEquals(expected, value);
    }

    @Test
    public void maxThrusterLoop1() {
        // TODO !! Step 2
        int phase = 98765;
        String input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
        int[] phaseInput = Util.toDigits(phase, 5);
        int expected = 139629729;

        ThrusterSeq ts = new ThrusterSeq(phaseInput, input);
        int value = ts.runThrusters();

        Assert.assertEquals(expected, value);
    }
}
