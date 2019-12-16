package org.advent;

import org.advent.util.Util;
import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
        String input = "03036732577212944063491565474664";
        byte[] origInput = Util.toDigitsByte(input);

        int offset =  UtilFft.getMsgOffset(origInput);
        System.out.println("Offset: " + offset);

        byte[] inTeK = UtilFft.tenK(origInput);
        FFTOpt real = new FFTOpt(inTeK, offset);
        real.DEBUG = true;
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "/100");
            real.phase();
        }
        byte[] result = real.getCurrent();

        System.out.println("result: " + Arrays.toString(result));

        Assert.assertEquals(8, result[0]);
        Assert.assertEquals(4, result[1]);
        Assert.assertEquals(4, result[2]);
        Assert.assertEquals(6, result[3]);
        Assert.assertEquals(2, result[4]);
        Assert.assertEquals(0, result[5]);
        Assert.assertEquals(2, result[6]);
        Assert.assertEquals(6, result[7]);
    }

    @Test
    public void step2_2() {
        String input = "02935109699940807407585447034323";
        byte[] origInput = Util.toDigitsByte(input);

        int offset =  UtilFft.getMsgOffset(origInput);
        System.out.println("Offset: " + offset);

        byte[] inTeK = UtilFft.tenK(origInput);
        FFTOpt real = new FFTOpt(inTeK, offset);
     //   real.DEBUG = true;
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "/100");
            real.phase();
        }
        byte[] result = real.getCurrent();

        System.out.println("result: " + Arrays.toString(result));

        Assert.assertEquals(7, result[0]);
        Assert.assertEquals(8, result[1]);
        Assert.assertEquals(7, result[2]);
        Assert.assertEquals(2, result[3]);
        Assert.assertEquals(5, result[4]);
        Assert.assertEquals(2, result[5]);
        Assert.assertEquals(7, result[6]);
        Assert.assertEquals(0, result[7]);
    }

    @Test
    public void step2_3() {
        String input = "03081770884921959731165446850517";
        byte[] origInput = Util.toDigitsByte(input);

        int offset =  UtilFft.getMsgOffset(origInput);
        System.out.println("Offset: " + offset);

        byte[] inTeK = UtilFft.tenK(origInput);
        FFTOpt real = new FFTOpt(inTeK, offset);
        //   real.DEBUG = true;
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "/100");
            real.phase();
        }
        byte[] result = real.getCurrent();

        System.out.println("result: " + Arrays.toString(result));

        Assert.assertEquals(5, result[0]);
        Assert.assertEquals(3, result[1]);
        Assert.assertEquals(5, result[2]);
        Assert.assertEquals(5, result[3]);
        Assert.assertEquals(3, result[4]);
        Assert.assertEquals(7, result[5]);
        Assert.assertEquals(3, result[6]);
        Assert.assertEquals(1, result[7]);
    }
}
