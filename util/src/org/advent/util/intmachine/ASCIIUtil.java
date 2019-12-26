package org.advent.util.intmachine;

public class ASCIIUtil {

    public static char A = 'A';
    public static char B = 'B';
    public static char C = 'C';
    public static char D = 'D';
    public static char E = 'E';
    public static char F = 'F';
    public static char G = 'G';
    public static char H = 'H';
    public static char I = 'I';
    public static char J = 'J';
    public static char L = 'L';
    public static char T = 'T';
    public static char N = 'N';
    public static char R = 'R';
    public static char ZERO = '0';
    public static char ONE = '1';
    public static char TWO = '2';
    public static char THREE = '3';
    public static char FOUR = '4';
    public static char FIVE = '5';
    public static char SIX = '6';
    public static char SEVEN = '7';
    public static char EIGHT = '8';
    public static char NINE = '9';
    public static char COMMA = ',';
    public static char SPACE = ' ';
    public static char NEW_LINE = 10;
    public static char YES = 'y';
    public static char NO = 'n';

    public static void addInputAndSeparator(IntMachine im, char in) {
        im.addInput(in);
        im.addInput(COMMA);
    }

    public static void addTen(IntMachine im, boolean separator) {
        im.addInput(ONE);
        if (separator) {
            addInputAndSeparator(im, ZERO);
        } else {
            im.addInput(ZERO);
        }
    }

    public static void addTwelve(IntMachine im, boolean separator) {
        im.addInput(ONE);
        if (separator) {
            addInputAndSeparator(im, TWO);
        } else {
            im.addInput(TWO);
        }
    }

    public static void and(IntMachine im, char a, char b) {
        im.addInput(A);
        im.addInput(N);
        im.addInput(D);
        im.addInput(SPACE);
        im.addInput(a);
        im.addInput(SPACE);
        im.addInput(b);
        im.addInput(NEW_LINE);
    }

    public static void or(IntMachine im, char a, char b) {
        im.addInput('O');
        im.addInput('R');
        im.addInput(SPACE);
        im.addInput(a);
        im.addInput(SPACE);
        im.addInput(b);
        im.addInput(NEW_LINE);
    }

    public static void not(IntMachine im, char a, char b) {
        im.addInput('N');
        im.addInput('O');
        im.addInput('T');
        im.addInput(SPACE);
        im.addInput(a);
        im.addInput(SPACE);
        im.addInput(b);
        im.addInput(NEW_LINE);
    }

    public static void walk(IntMachine im) {
        im.addInput('W');
        im.addInput(A);
        im.addInput(L);
        im.addInput('K');
        im.addInput(NEW_LINE);
    }

    public static void run(IntMachine im) {
        im.addInput('R');
        im.addInput('U');
        im.addInput('N');
        im.addInput(NEW_LINE);
    }

    public static void west(IntMachine im) {
        im.addInput('w');
        im.addInput('e');
        im.addInput('s');
        im.addInput('t');
        im.addInput(NEW_LINE);
    }

    public static void east(IntMachine im) {
        im.addInput('e');
        im.addInput('a');
        im.addInput('s');
        im.addInput('t');
        im.addInput(NEW_LINE);
    }

    public static void north(IntMachine im) {
        im.addInput('n');
        im.addInput('o');
        im.addInput('r');
        im.addInput('t');
        im.addInput('h');
        im.addInput(NEW_LINE);
    }

    public static void south(IntMachine im) {
        im.addInput('s');
        im.addInput('o');
        im.addInput('u');
        im.addInput('t');
        im.addInput('h');
        im.addInput(NEW_LINE);
    }

    public static void polygon(IntMachine im, boolean take) {
        takeManual(im, take, "polygon");
    }

    public static void fixedPoint(IntMachine im, boolean take) {
        takeManual(im, take, "fixed point");
    }

    public static void moltenLava(IntMachine im, boolean take) {
        takeManual(im, take, "molten lava");
    }

    public static void aic(IntMachine im, boolean take) {
        takeManual(im, take, "astronaut ice cream");
    }

    public static void easterEgg(IntMachine im, boolean take) {
        takeManual(im, take, "easter egg");
    }

    public static void photons(IntMachine im, boolean take) {
        takeManual(im, take, "photons");
    }

    public static void darkMatter(IntMachine im, boolean take) {
        takeManual(im, take, "dark matter");
    }

    public static void escapePod(IntMachine im, boolean take) {
        takeManual(im, take, "escape pod");
    }

    public static void foodRations(IntMachine im, boolean take) {
        takeManual(im, take, "food ration");
    }

    public static void asterisk(IntMachine im, boolean take) {
        takeManual(im, take, "asterisk");
    }

    public static void weatherMachine(IntMachine im, boolean take) {
        takeManual(im, take, "weather machine");
    }


    public static void takeManual(IntMachine im, boolean take, String line) {
        if (take) {
            manual(im, "take " + line);
        } else {
            manual(im, "drop " + line);
        }
    }

    public static void manual(IntMachine im, String line) {
        for (int i = 0; i < line.length(); i++) {
            im.addInput(line.charAt(i));
        }
        im.addInput(NEW_LINE);
    }
}
