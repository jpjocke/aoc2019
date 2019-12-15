package org.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DroidManualMode implements DroidMode {
    Labyrinth labyrinth;
    Droid d;
    BufferedReader br;

    public DroidManualMode(Labyrinth labyrinth, Droid d) {
        this.labyrinth = labyrinth;
        this.d = d;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private int getDirection() throws IOException {
        System.out.println("w=UP, s=DOWN, a=LEFT, d=RIGHT p=QUIT o=Automate 10 steps");
        System.out.print("Dir: ");
        String s = br.readLine();

        if (s.equals("p")) {
            return -1;
        }
        if (s.equals("w")) {
            return 1;
        }
        if (s.equals("s")) {
            return 2;
        }
        if (s.equals("a")) {
            return 3;
        }
        if (s.equals("d")) {
            return 4;
        }

        if (s.equals("o")) {
            return 50;
        }
        return 5;
    }

    @Override
    public int getNextDir() throws IOException {
        return getDirection();
    }
}
