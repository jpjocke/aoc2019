package org.advent;

import org.advent.util.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Verify {


    @Test
    public void test1() {
        int input = 12345678;
        FFT fft = new FFT(Util.toDigitsByte(input));
        fft.phase();
        byte[] result = fft.getCurrent();

        byte[] expected = Util.toDigitsByte(48226158);
        // phase 1
        Assert.assertTrue(Arrays.equals(expected, result));

        // phase 2
        fft.phase();
        result = fft.getCurrent();
        expected = Util.toDigitsByte(34040438);
        Assert.assertTrue(Arrays.equals(expected, result));

        // phase 3
        fft.phase();
        result = fft.getCurrent();
        expected = Util.toDigitsByte(3415518, 8);
        Assert.assertTrue(Arrays.equals(expected, result));

        // phase 4
        fft.phase();
        result = fft.getCurrent();
        expected = Util.toDigitsByte(1029498, 8);
        Assert.assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void test2() {
        String input = "80871224585914546619083218645595";
        byte[] orig = Util.toDigitsByte(input);
        FFT fft = new FFT(Util.toDigitsByte(input));
        fft.DEBUG = true;
        List<byte[]> phases = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fft.phase();
            byte[] result = fft.getCurrent();
            phases.add(Arrays.copyOf(result, result.length));
        }
        byte[] result = fft.getCurrent();

        System.out.println("-----------------------------");
        System.out.println(Arrays.toString(orig));
        System.out.println("-----------------------------");
        for (int i = 0; i < phases.size(); i++) {
            System.out.println(Arrays.toString(phases.get(i)));
        }

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
        // tar alldelles för lång tid
        String input = "03036732577212944063491565474664";
        byte[] origInput = Util.toDigitsByte(input);

        int offset =  UtilFft.getMsgOffset(origInput);
        System.out.println("Offset: " + offset);

        byte[] inTeK = UtilFft.tenK(origInput);
        FFT real = new FFT(inTeK);
      //  real.DEBUG = true;
        // phase 100
        for (int i = 0; i < 100; i++) {
            real.phase();
        }
        byte[] result = real.getCurrent();
        byte[] msg = UtilFft.getMessageFromOffset(offset, result);

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
