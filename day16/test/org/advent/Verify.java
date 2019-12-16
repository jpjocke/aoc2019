package org.advent;

import org.advent.util.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class Verify {


    @Test
    public void test1() {
        int input = 12345678;
        FFT fft = new FFT(Util.toDigits(input, 8), 0);
        fft.phase();
        int[] result = fft.getCurrent();

        int[] expected = Util.toDigits(48226158, 8);
        // phase 1
        Assert.assertTrue(Arrays.equals(expected, result));

        // phase 2
        fft.phase();
        result = fft.getCurrent();
        expected = Util.toDigits(34040438, 8);
        Assert.assertTrue(Arrays.equals(expected, result));

        // phase 3
        fft.phase();
        result = fft.getCurrent();
        expected = Util.toDigits(3415518, 8);
        Assert.assertTrue(Arrays.equals(expected, result));

        // phase 4
        fft.phase();
        result = fft.getCurrent();
        expected = Util.toDigits(1029498, 8);
        Assert.assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void test2() {
        String input = "80871224585914546619083218645595";
        FFT fft = new FFT(Util.toDigits(input), 0);
        for (int i = 0; i < 100; i++) {

            fft.phase();
        }
        int[] result = fft.getCurrent();

        Assert.assertEquals(2, result[0]);
        Assert.assertEquals(4, result[1]);
        Assert.assertEquals(1, result[2]);
        Assert.assertEquals(7, result[3]);
        Assert.assertEquals(6, result[4]);
        Assert.assertEquals(1, result[5]);
        Assert.assertEquals(7, result[6]);
        Assert.assertEquals(6, result[7]);
    }

    @Test
    public void step2_1() {
        // TODO här är jag
        // ska denna loopas 10000 gånger????
        String input = "03036732577212944063491565474664";
        int[] origInput = Util.toDigits(input);
        FFT fft = new FFT(origInput, 0);
        for (int i = 0; i < 10000; i++) {
            fft.phase();
        }
        int[] realInput = fft.getCurrent();

        long offset =  FFT.getMsgOffset(origInput);
        System.out.println("Offset: " + offset);

        FFT real = new FFT(realInput, offset);
        // phase 100
        for (int i = 0; i < 100; i++) {
            real.phase();
        }
        int[] result = fft.getCurrent();
        int[] msg = fft.getMessageFromOffset();

        System.out.println("result: " + Arrays.toString(result));
        System.out.println("msg: " + Arrays.toString(msg));

        Assert.assertEquals(8, msg[0]);
        Assert.assertEquals(4, msg[1]);
        Assert.assertEquals(4, msg[2]);
        Assert.assertEquals(6, msg[3]);
        Assert.assertEquals(2, msg[4]);
        Assert.assertEquals(0, msg[5]);
        Assert.assertEquals(2, msg[6]);
        Assert.assertEquals(6, msg[7]);
    }
}
