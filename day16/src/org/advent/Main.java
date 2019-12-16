package org.advent;

import org.advent.util.Util;

import java.util.Arrays;

public class Main {

    static String input = "59796332430280528211060657577039744056609636505111313336094865900635343682296702094018432765613019371234483818415934914575717134617783515237300919201989706451524069044921384738930172026234872525254689609787752401342687098918804210494391161531008341329016626922990938854681575317559821067933058630688365067790812341475168200215494963690593873250884807840611487288560291748414160551508979374335267106414602526249401281066501677212002980616711058803845336067123298983258010079178111678678796586176705130938139337603538683803154824502761228184209473344607055926120829751532887810487164482307564712692208157118923502010028250886290873995577102178526942152";

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();
        FFT fft = new FFT(Util.toDigits(input), 0);

        // get real input
        for (int i = 0; i < 10000; i++) {
            fft.phase();
        }
        int[] realInput = fft.getCurrent();
        System.out.println("real input: " + Arrays.toString(realInput));


        FFT real = new FFT(realInput, 0);
        // phase 100
        for (int i = 0; i < 100; i++) {
            real.phase();
        }
        int[] result = fft.getCurrent();
        System.out.println("result: " + Arrays.toString(result));
        System.out.println("msg: " + Arrays.toString(real.getMessageFromOffset()));

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
        // 87899007 -> too high
    }

}
