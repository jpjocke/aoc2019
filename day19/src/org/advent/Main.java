package org.advent;

import org.advent.util.IntPoint;
import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

public class Main {
    static String input = "109,424,203,1,21102,1,11,0,1105,1,282,21102,1,18,0,1105,1,259,2102,1,1,221,203,1,21101,31,0,0,1105,1,282,21101,38,0,0,1105,1,259,20102,1,23,2,21201,1,0,3,21102,1,1,1,21101,57,0,0,1106,0,303,2101,0,1,222,20101,0,221,3,21001,221,0,2,21101,259,0,1,21101,80,0,0,1106,0,225,21102,1,97,2,21102,1,91,0,1105,1,303,1201,1,0,223,20102,1,222,4,21101,259,0,3,21101,225,0,2,21102,1,225,1,21102,1,118,0,1106,0,225,21001,222,0,3,21102,1,21,2,21102,133,1,0,1106,0,303,21202,1,-1,1,22001,223,1,1,21101,0,148,0,1105,1,259,1201,1,0,223,20101,0,221,4,21001,222,0,3,21101,14,0,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21101,195,0,0,106,0,109,20207,1,223,2,20102,1,23,1,21102,-1,1,3,21101,0,214,0,1106,0,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,2101,0,-4,249,21201,-3,0,1,21202,-2,1,2,21201,-1,0,3,21101,0,250,0,1106,0,225,22101,0,1,-4,109,-5,2105,1,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2105,1,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,21202,-2,1,-2,109,-3,2105,1,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,22102,1,-2,3,21101,0,343,0,1105,1,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,22101,0,-4,1,21102,1,384,0,1106,0,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,21202,1,1,-4,109,-5,2106,0,0";

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();

        TractorSpace b = new TractorSpace(50);

        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                IntCodes ops = new IntCodes(input);
                IntMachine im = new IntMachine(ops);

                im.addInput(x);
                im.addInput(y);

                im.execute();
                b.setValueAt(x, y, (int) im.getLastOutput());
            }
        }

        b.print();

        System.out.println("Affected spaces: " + b.countOnes());

        b.findVectors();
        b.printByVectors();
      //  IntPoint pos = findOneHundredByY(b);
       // testPos(pos, b);
        testPos(new IntPoint(502, 1156), b);

        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

    private static IntPoint findOneHundredByY(TractorSpace b) {
        for (int i = 100; i < 400; i++) {
            System.out.print(b.tractorByVector(700, i));
        }

        System.out.println("Running test");
        IntPoint pos = new IntPoint(0, 0);
        int y = 800;
        while (true) {
            System.out.println("Testing y: " + y);
            int x = 0;
            int ret = 0;
            while (ret == 0) {
                x++;
                ret = b.tractorByVector(y, x);
            }
            // x = first tractor;
            int innerY = y;
            while (ret == 1 && innerY > y - 100) {
                innerY--;
                ret = b.tractorByVector(innerY, x);
            }
            if (innerY == y - 100) {
                pos.y = innerY;
                // ok found 100 up
                int innerX = x;
                System.out.println("Testing x: " + x);
                while (ret == 1 && innerX - x < 100) {
                    innerX++;
                    ret = b.tractorByVector(innerY, innerX);
                }
                if (innerX - x == 100) {
                    pos.x = x;
                    System.out.println("Found space at: x: " + x + " y: " + innerY);
                    System.out.println("Result:" + (x * 10000 + innerY));
                    // 6011152 too high
                    // 5011152 too high
                    // 5001150 wrong
                    // 5041160 wrong
                    // 5001153 wrong
                    // 5021156 wrong
                    // 4500975 wrong -> approxing by taking 4x4
                    // 3500850 wrong -> real 4x4?
                    // 3750850 fel
                    // 3750850 fel
                    break;
                }
            }
            y++;
        }
        return pos;
    }

    public static void testPos(IntPoint pos, TractorSpace b) {
        for (int y = pos.y; y < pos.y + 110; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = pos.x; x < pos.x + 110; x++) {
                sb.append(b.tractorByVector(y, x) == 1 ? 'X' : '.');
            }
            System.out.println(sb.toString());
        }
    }

}
