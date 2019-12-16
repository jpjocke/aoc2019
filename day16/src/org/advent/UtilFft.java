package org.advent;

public class UtilFft {

    public static int getMsgOffset(byte[] input) {
        String offset = "";
        for (int i = 0; i < 7; i++) {
            offset = offset + input[i];
        }

        return Integer.parseInt(offset);
    }

    public static byte[] tenK(byte[] input) {
        byte[] tenK = new byte[input.length * 10000];

        for(int i = 0; i < tenK.length; i++) {
            tenK[i] = input[i % input.length];
        }

        return tenK;
    }


    public static byte[] getMessageFromOffset(int offset, byte[] codes) {
        byte[] msg = new byte[8];
        for (int i = 0; i < msg.length; i++) {
            msg[i] = codes[offset];
            offset++;
        }
        return msg;
    }
}
