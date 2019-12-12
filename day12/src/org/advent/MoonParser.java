package org.advent;

import org.advent.util.IntVector;

import java.util.ArrayList;
import java.util.List;

public class MoonParser {

    public static List<Moon> parse(String input) {
        String[] split = input.split("\n");
        List<Moon> moons = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String[] a = split[i].split(" ");
            int x = getValue(a[0]);
            int y = getValue(a[1]);
            int z = getValue(a[2]);
            IntVector pos = new IntVector(x, y, z);
            moons.add(new Moon(pos));
        }
        return moons;
    }

    private static int getValue(String a) {
        String[] split = a.split("=");
        String v = split[1].replace(",", "");
        v = v.replace(">", "");
        return Integer.parseInt(v);
    }
}
