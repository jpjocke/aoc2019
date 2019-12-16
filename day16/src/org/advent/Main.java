package org.advent;

import org.advent.util.Util;

import java.util.Arrays;

public class Main {

    static String input = "59796332430280528211060657577039744056609636505111313336094865900635343682296702094018432765613019371234483818415934914575717134617783515237300919201989706451524069044921384738930172026234872525254689609787752401342687098918804210494391161531008341329016626922990938854681575317559821067933058630688365067790812341475168200215494963690593873250884807840611487288560291748414160551508979374335267106414602526249401281066501677212002980616711058803845336067123298983258010079178111678678796586176705130938139337603538683803154824502761228184209473344607055926120829751532887810487164482307564712692208157118923502010028250886290873995577102178526942152";

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();
        //step1();
        step2();

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
        // 87899007 -> too high
    }

    private static void step1() {
        FFT fft = new FFT(Util.toDigitsByte(input));
        for (int i = 0; i < 100; i++) {
            fft.phase();
        }
    }

    private static void step2() {
        byte[] origInput = Util.toDigitsByte(input);
        int offset =  UtilFft.getMsgOffset(origInput);

        byte[] inTeK = UtilFft.tenK(origInput);
        FFTOpt real = new FFTOpt(inTeK, offset);
        real.DEBUG = true;
        // phase 100
        for (int i = 0; i < 100; i++) {
            real.phase();
        }
        byte[] result = real.getCurrent();

        for(int i = 0; i < 8; i++) {
            System.out.println("result: " + result[i]);
        }

      //  System.out.println("result: " + Arrays.toString(result));
    }

}
