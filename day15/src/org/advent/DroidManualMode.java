package org.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DroidManualMode {
    Labyrinth labyrinth;
    Droid d;
    BufferedReader br;

    public DroidManualMode(Labyrinth labyrinth, Droid d) {
        this.labyrinth = labyrinth;
        this.d = d;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void goManual() throws IOException {
        while (true) {
            int dir = getDirection();
            if(dir == -1) {
                break;
            }
            if(dir == 5) {
                continue;
            }
            d.runAndReport(dir);
        }
    }

    private int getDirection() throws IOException {
        System.out.println("w=UP, s=DOWN, a=LEFT, d=RIGHT p=QUIT ");
        System.out.print("Dir: w ");
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
        return 5;
    }
}
