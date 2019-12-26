package org.advent;

import org.advent.util.intmachine.ASCIIUtil;
import org.advent.util.intmachine.IntMachine;
import org.advent.util.intmachine.Output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DroidManual {

    BufferedReader br;
    private IntMachine im;

    public DroidManual(IntMachine im) {
        this.im = im;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {

        Output output = im.getOutputObj();
        while (true) {
            im.execute();
            printMessages(output.getFullOutput());
            output.clear();
            int r = getInput();
            if (r == -1) {
                break;
            }
        }
    }

    public void pickup(boolean[] items) {
        ASCIIUtil.west(im);
        im.execute();
        ASCIIUtil.south(im);
        im.execute();
        ASCIIUtil.south(im);
        im.execute();
        ASCIIUtil.south(im);
        im.execute();
        if (items[10]) {
            ASCIIUtil.asterisk(im, true);
            im.execute();
        }
        ASCIIUtil.north(im);
        im.execute();
        ASCIIUtil.north(im);
        im.execute();
        ASCIIUtil.north(im);
        im.execute();
        ASCIIUtil.west(im);
        im.execute();
        if (items[0]) {

            ASCIIUtil.photons(im, true);
            im.execute();
        }
        ASCIIUtil.west(im);
        im.execute();
        ASCIIUtil.west(im);
        im.execute();
        if (items[1]) {
            ASCIIUtil.darkMatter(im, true);
            im.execute();
        }
        ASCIIUtil.east(im);
        im.execute();
        ASCIIUtil.south(im);
        im.execute();
        if (items[2]) {
            ASCIIUtil.fixedPoint(im, true);
            im.execute();
        }
        ASCIIUtil.west(im);
        im.execute();
        if (items[3]) {
            ASCIIUtil.foodRations(im, true);
            im.execute();
        }
        ASCIIUtil.east(im);
        im.execute();
        ASCIIUtil.north(im);
        im.execute();
        ASCIIUtil.east(im);
        im.execute();
        ASCIIUtil.south(im);
        im.execute();
        if (items[4]) {
            ASCIIUtil.aic(im, true);
            im.execute();
        }
        ASCIIUtil.west(im);
        im.execute();
        if (items[5]) {
            ASCIIUtil.moltenLava(im, true);
            im.execute();
        }
        ASCIIUtil.east(im);
        im.execute();
        ASCIIUtil.south(im);
        im.execute();
        if (items[6]) {
            ASCIIUtil.polygon(im, true);
            im.execute();
        }
        ASCIIUtil.east(im);
        im.execute();
        if (items[7]) {
            ASCIIUtil.easterEgg(im, true);
            im.execute();
        }
        ASCIIUtil.north(im);
        im.execute();
        if (items[8]) {
            ASCIIUtil.escapePod(im, true);
            im.execute();
        }
        ASCIIUtil.south(im);
        im.execute();
        ASCIIUtil.east(im);
        im.execute();
        if (items[9]) {
            ASCIIUtil.weatherMachine(im, true);
            im.execute();
        }
        ASCIIUtil.north(im);
        im.execute();
        im.getOutputObj().clear();
    }

    public void runWithItems() throws IOException {
        Output output = im.getOutputObj();

        ASCIIUtil.manual(im, "inv");
        im.execute();
        String inv = getOutput(output.getFullOutput());
        System.out.println(inv.substring(25, inv.length() - 10));

        output.clear();
        ASCIIUtil.north(im);
        im.execute();

        String o = getOutput(output.getFullOutput());
       // System.out.println(o);
        if(o.contains("heavier")) {
            System.out.println("Too little stuff");
        }
        if (o.contains("lighter")) {

            System.out.println("Too much stuff");
        }
        System.out.println("##");

        if (o.contains("and you are ejected")) {
            return;
        }

        System.out.println(o);
        run();

    }

    private int getInput() throws IOException {
        System.out.println("w=north, s=south, a=west, d=east p=QUIT else manual");
        System.out.print("Dir: ");
        String s = br.readLine();

        if (s.equals("p")) {
            return -1;
        } else if (s.equals("w")) {
            ASCIIUtil.north(im);
        } else if (s.equals("s")) {
            ASCIIUtil.south(im);
        } else if (s.equals("a")) {
            ASCIIUtil.west(im);
        } else if (s.equals("d")) {
            ASCIIUtil.east(im);
        } else if (s.equals("0")) {
            ASCIIUtil.polygon(im, true);
        } else if (s.equals("00")) {
            ASCIIUtil.polygon(im, false);
        } else if (s.equals("1")) {
            ASCIIUtil.fixedPoint(im, true);
        } else if (s.equals("11")) {
            ASCIIUtil.fixedPoint(im, false);
        } else if (s.equals("2")) {
            ASCIIUtil.moltenLava(im, true);
        } else if (s.equals("22")) {
            ASCIIUtil.moltenLava(im, false);
        } else if (s.equals("3")) {
            ASCIIUtil.aic(im, true);
        } else if (s.equals("33")) {
            ASCIIUtil.aic(im, false);
        } else if (s.equals("4")) {
            ASCIIUtil.easterEgg(im, true);
        } else if (s.equals("44")) {
            ASCIIUtil.easterEgg(im, false);
        } else if (s.equals("5")) {
            ASCIIUtil.photons(im, true);
        } else if (s.equals("55")) {
            ASCIIUtil.photons(im, false);
        } else if (s.equals("6")) {
            ASCIIUtil.darkMatter(im, true);
        } else if (s.equals("66")) {
            ASCIIUtil.darkMatter(im, false);
        } else if (s.equals("7")) {
            ASCIIUtil.escapePod(im, true);
        } else if (s.equals("77")) {
            ASCIIUtil.escapePod(im, false);
        } else if (s.equals("8")) {
            ASCIIUtil.foodRations(im, true);
        } else if (s.equals("88")) {
            ASCIIUtil.foodRations(im, false);
        } else if (s.equals("9")) {
            ASCIIUtil.asterisk(im, true);
        } else if (s.equals("99")) {
            ASCIIUtil.asterisk(im, false);
        } else if (s.equals("10")) {
            ASCIIUtil.weatherMachine(im, true);
        } else if (s.equals("1010")) {
            ASCIIUtil.weatherMachine(im, false);
        } else {
            ASCIIUtil.manual(im, s);
        }
        return 1;
    }

    private String getOutput(List<Long> output) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < output.size(); i++) {
            char c = new Character((char) output.get(i).intValue());

            sb.append(c + "");
        }
        return sb.toString();
    }

    private void printMessages(List<Long> output) {
        System.out.println("--------  MESSAGE --------------");
        /*
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
         */
        System.out.println(getOutput(output));
    }
}
