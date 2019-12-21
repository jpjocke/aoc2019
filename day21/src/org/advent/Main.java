package org.advent;

import org.advent.util.intmachine.ASCIIUtil;
import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

import java.util.List;

public class Main {
    private static String input = "109,2050,21102,1,966,1,21102,13,1,0,1106,0,1378,21102,1,20,0,1106,0,1337,21102,1,27,0,1106,0,1279,1208,1,65,748,1005,748,73,1208,1,79,748,1005,748,110,1208,1,78,748,1005,748,132,1208,1,87,748,1005,748,169,1208,1,82,748,1005,748,239,21102,1041,1,1,21101,0,73,0,1106,0,1421,21101,0,78,1,21102,1,1041,2,21101,0,88,0,1105,1,1301,21101,0,68,1,21101,0,1041,2,21102,1,103,0,1105,1,1301,1101,0,1,750,1105,1,298,21102,1,82,1,21102,1041,1,2,21102,1,125,0,1106,0,1301,1102,1,2,750,1105,1,298,21101,79,0,1,21101,1041,0,2,21102,147,1,0,1105,1,1301,21101,0,84,1,21102,1041,1,2,21102,162,1,0,1105,1,1301,1101,0,3,750,1105,1,298,21101,65,0,1,21101,1041,0,2,21101,184,0,0,1106,0,1301,21101,76,0,1,21101,0,1041,2,21102,1,199,0,1106,0,1301,21101,0,75,1,21102,1,1041,2,21102,214,1,0,1105,1,1301,21101,221,0,0,1105,1,1337,21101,0,10,1,21102,1041,1,2,21101,236,0,0,1106,0,1301,1105,1,553,21102,85,1,1,21101,1041,0,2,21102,254,1,0,1105,1,1301,21102,1,78,1,21102,1041,1,2,21101,269,0,0,1106,0,1301,21102,276,1,0,1105,1,1337,21101,0,10,1,21101,0,1041,2,21101,291,0,0,1106,0,1301,1102,1,1,755,1106,0,553,21102,1,32,1,21101,1041,0,2,21101,0,313,0,1106,0,1301,21102,1,320,0,1105,1,1337,21101,0,327,0,1106,0,1279,2102,1,1,749,21102,1,65,2,21102,73,1,3,21101,346,0,0,1106,0,1889,1206,1,367,1007,749,69,748,1005,748,360,1102,1,1,756,1001,749,-64,751,1105,1,406,1008,749,74,748,1006,748,381,1102,-1,1,751,1105,1,406,1008,749,84,748,1006,748,395,1101,-2,0,751,1106,0,406,21102,1100,1,1,21101,0,406,0,1105,1,1421,21101,0,32,1,21101,1100,0,2,21102,421,1,0,1105,1,1301,21101,0,428,0,1105,1,1337,21102,435,1,0,1106,0,1279,2101,0,1,749,1008,749,74,748,1006,748,453,1101,-1,0,752,1106,0,478,1008,749,84,748,1006,748,467,1102,-2,1,752,1106,0,478,21102,1168,1,1,21101,478,0,0,1106,0,1421,21102,1,485,0,1105,1,1337,21101,0,10,1,21101,1168,0,2,21101,0,500,0,1106,0,1301,1007,920,15,748,1005,748,518,21101,1209,0,1,21101,518,0,0,1106,0,1421,1002,920,3,529,1001,529,921,529,1002,750,1,0,1001,529,1,537,102,1,751,0,1001,537,1,545,1002,752,1,0,1001,920,1,920,1106,0,13,1005,755,577,1006,756,570,21101,1100,0,1,21102,570,1,0,1106,0,1421,21102,987,1,1,1105,1,581,21101,0,1001,1,21102,1,588,0,1106,0,1378,1102,758,1,593,1001,0,0,753,1006,753,654,20101,0,753,1,21102,1,610,0,1106,0,667,21102,0,1,1,21101,0,621,0,1105,1,1463,1205,1,647,21102,1,1015,1,21101,635,0,0,1106,0,1378,21102,1,1,1,21102,1,646,0,1105,1,1463,99,1001,593,1,593,1106,0,592,1006,755,664,1101,0,0,755,1105,1,647,4,754,99,109,2,1101,726,0,757,22102,1,-1,1,21102,1,9,2,21102,1,697,3,21102,1,692,0,1106,0,1913,109,-2,2106,0,0,109,2,1001,757,0,706,2101,0,-1,0,1001,757,1,757,109,-2,2105,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,255,63,127,223,95,191,159,0,157,56,156,254,179,109,222,168,35,248,163,202,84,58,140,175,53,253,142,143,247,43,203,102,167,110,155,219,125,174,57,229,173,38,114,182,69,189,242,190,185,232,46,122,136,87,126,154,137,213,169,178,216,152,71,207,101,183,238,113,39,177,230,118,123,62,103,218,139,252,34,221,206,107,234,186,79,231,117,153,204,243,201,188,184,115,217,250,228,198,94,61,77,170,121,239,47,199,171,244,93,51,205,236,237,226,214,158,50,59,220,116,197,181,100,60,70,99,54,78,108,119,124,138,98,162,246,68,120,166,241,233,49,245,86,111,249,212,215,55,85,196,235,251,172,227,187,76,42,141,106,92,200,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20,73,110,112,117,116,32,105,110,115,116,114,117,99,116,105,111,110,115,58,10,13,10,87,97,108,107,105,110,103,46,46,46,10,10,13,10,82,117,110,110,105,110,103,46,46,46,10,10,25,10,68,105,100,110,39,116,32,109,97,107,101,32,105,116,32,97,99,114,111,115,115,58,10,10,58,73,110,118,97,108,105,100,32,111,112,101,114,97,116,105,111,110,59,32,101,120,112,101,99,116,101,100,32,115,111,109,101,116,104,105,110,103,32,108,105,107,101,32,65,78,68,44,32,79,82,44,32,111,114,32,78,79,84,67,73,110,118,97,108,105,100,32,102,105,114,115,116,32,97,114,103,117,109,101,110,116,59,32,101,120,112,101,99,116,101,100,32,115,111,109,101,116,104,105,110,103,32,108,105,107,101,32,65,44,32,66,44,32,67,44,32,68,44,32,74,44,32,111,114,32,84,40,73,110,118,97,108,105,100,32,115,101,99,111,110,100,32,97,114,103,117,109,101,110,116,59,32,101,120,112,101,99,116,101,100,32,74,32,111,114,32,84,52,79,117,116,32,111,102,32,109,101,109,111,114,121,59,32,97,116,32,109,111,115,116,32,49,53,32,105,110,115,116,114,117,99,116,105,111,110,115,32,99,97,110,32,98,101,32,115,116,111,114,101,100,0,109,1,1005,1262,1270,3,1262,21001,1262,0,0,109,-1,2105,1,0,109,1,21101,0,1288,0,1106,0,1263,20101,0,1262,0,1101,0,0,1262,109,-1,2105,1,0,109,5,21101,0,1310,0,1105,1,1279,22102,1,1,-2,22208,-2,-4,-1,1205,-1,1332,22101,0,-3,1,21101,0,1332,0,1105,1,1421,109,-5,2105,1,0,109,2,21101,1346,0,0,1105,1,1263,21208,1,32,-1,1205,-1,1363,21208,1,9,-1,1205,-1,1363,1105,1,1373,21102,1370,1,0,1105,1,1279,1105,1,1339,109,-2,2106,0,0,109,5,2101,0,-4,1386,20101,0,0,-2,22101,1,-4,-4,21101,0,0,-3,22208,-3,-2,-1,1205,-1,1416,2201,-4,-3,1408,4,0,21201,-3,1,-3,1106,0,1396,109,-5,2106,0,0,109,2,104,10,22102,1,-1,1,21101,0,1436,0,1105,1,1378,104,10,99,109,-2,2106,0,0,109,3,20002,593,753,-1,22202,-1,-2,-1,201,-1,754,754,109,-3,2106,0,0,109,10,21101,0,5,-5,21102,1,1,-4,21102,1,0,-3,1206,-9,1555,21101,3,0,-6,21101,0,5,-7,22208,-7,-5,-8,1206,-8,1507,22208,-6,-4,-8,1206,-8,1507,104,64,1106,0,1529,1205,-6,1527,1201,-7,716,1515,21002,0,-11,-8,21201,-8,46,-8,204,-8,1105,1,1529,104,46,21201,-7,1,-7,21207,-7,22,-8,1205,-8,1488,104,10,21201,-6,-1,-6,21207,-6,0,-8,1206,-8,1484,104,10,21207,-4,1,-8,1206,-8,1569,21102,1,0,-9,1105,1,1689,21208,-5,21,-8,1206,-8,1583,21101,1,0,-9,1106,0,1689,1201,-5,716,1589,20102,1,0,-2,21208,-4,1,-1,22202,-2,-1,-1,1205,-2,1613,22102,1,-5,1,21101,0,1613,0,1105,1,1444,1206,-1,1634,22102,1,-5,1,21101,1627,0,0,1106,0,1694,1206,1,1634,21101,2,0,-3,22107,1,-4,-8,22201,-1,-8,-8,1206,-8,1649,21201,-5,1,-5,1206,-3,1663,21201,-3,-1,-3,21201,-4,1,-4,1105,1,1667,21201,-4,-1,-4,21208,-4,0,-1,1201,-5,716,1676,22002,0,-1,-1,1206,-1,1686,21102,1,1,-4,1105,1,1477,109,-10,2106,0,0,109,11,21102,0,1,-6,21102,1,0,-8,21102,0,1,-7,20208,-6,920,-9,1205,-9,1880,21202,-6,3,-9,1201,-9,921,1725,20101,0,0,-5,1001,1725,1,1732,21001,0,0,-4,21202,-4,1,1,21102,1,1,2,21102,9,1,3,21102,1754,1,0,1105,1,1889,1206,1,1772,2201,-10,-4,1766,1001,1766,716,1766,21002,0,1,-3,1106,0,1790,21208,-4,-1,-9,1206,-9,1786,21202,-8,1,-3,1106,0,1790,21202,-7,1,-3,1001,1732,1,1795,21002,0,1,-2,21208,-2,-1,-9,1206,-9,1812,22101,0,-8,-1,1105,1,1816,21202,-7,1,-1,21208,-5,1,-9,1205,-9,1837,21208,-5,2,-9,1205,-9,1844,21208,-3,0,-1,1106,0,1855,22202,-3,-1,-1,1106,0,1855,22201,-3,-1,-1,22107,0,-1,-1,1105,1,1855,21208,-2,-1,-9,1206,-9,1869,22101,0,-1,-8,1105,1,1873,22102,1,-1,-7,21201,-6,1,-6,1105,1,1708,21202,-8,1,-10,109,-11,2106,0,0,109,7,22207,-6,-5,-3,22207,-4,-6,-2,22201,-3,-2,-1,21208,-1,0,-6,109,-7,2106,0,0,0,109,5,1201,-2,0,1912,21207,-4,0,-1,1206,-1,1930,21102,1,0,-4,22101,0,-4,1,22101,0,-3,2,21101,0,1,3,21102,1949,1,0,1105,1,1954,109,-5,2105,1,0,109,6,21207,-4,1,-1,1206,-1,1977,22207,-5,-3,-1,1206,-1,1977,22102,1,-5,-5,1105,1,2045,21201,-5,0,1,21201,-4,-1,2,21202,-3,2,3,21101,0,1996,0,1106,0,1954,21201,1,0,-5,21101,1,0,-2,22207,-5,-3,-1,1206,-1,2015,21101,0,0,-2,22202,-3,-2,-3,22107,0,-4,-1,1206,-1,2037,21202,-2,1,1,21101,0,2037,0,106,0,1912,21202,-3,-1,-3,22201,-5,-3,-5,109,-6,2105,1,0";

    public static void main(String[] args) {
        long tick = System.currentTimeMillis();

        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        //step1(im);
        step2(im);


        System.out.println("time: " + (System.currentTimeMillis() - tick) + " ms");
    }

    private static void step1(IntMachine im) {
        ASCIIUtil.not(im, ASCIIUtil.A, ASCIIUtil.T);
        ASCIIUtil.not(im, ASCIIUtil.B, ASCIIUtil.J);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
        ASCIIUtil.not(im, ASCIIUtil.C, ASCIIUtil.T);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J); // om a|b|c = 0 så är j 0 nu
        ASCIIUtil.and(im, ASCIIUtil.D, ASCIIUtil.J);
        ASCIIUtil.walk(im);
        im.execute();

        List<Long> output = im.getOutput();
        printMessages(output);
    }

    private static void step2(IntMachine im) {
        ASCIIUtil.not(im, ASCIIUtil.A, ASCIIUtil.T);
        ASCIIUtil.not(im, ASCIIUtil.B, ASCIIUtil.J);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
        ASCIIUtil.not(im, ASCIIUtil.C, ASCIIUtil.T);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
        ASCIIUtil.not(im, ASCIIUtil.D, ASCIIUtil.T);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
        ASCIIUtil.not(im, ASCIIUtil.E, ASCIIUtil.T);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
        ASCIIUtil.not(im, ASCIIUtil.F, ASCIIUtil.T);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
        ASCIIUtil.not(im, ASCIIUtil.G, ASCIIUtil.T);
        ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);
       // ASCIIUtil.not(im, ASCIIUtil.H, ASCIIUtil.T);
      //  ASCIIUtil.or(im, ASCIIUtil.T, ASCIIUtil.J);

        ASCIIUtil.and(im, ASCIIUtil.H, ASCIIUtil.J);
      //  ASCIIUtil.and(im, ASCIIUtil.I, ASCIIUtil.J);
        ASCIIUtil.run(im);
        im.execute();

        List<Long> output = im.getOutput();
        printMessages(output);
    }

    private static void printMessages(List<Long> output) {
        System.out.println("--------  MESSAGE --------------");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < output.size(); i++) {
            char c = new Character((char) output.get(i).intValue());
            if (c == 10) {
                System.out.println(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            sb.append(c + "");
           // sb.append(c + "(" + output.get(i).intValue() + ")");
            // System.out.println("i: " + i + ", val: " + output.get(i) + ", " + c);
        }
        System.out.println(sb.toString());
    }

}
