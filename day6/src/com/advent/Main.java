package com.advent;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> input = ReadFile.getInput();
        MapParser parser = new MapParser(input);
        SpaceObject root = parser.parseMap();
        int orbits = MapCalculator.calculateTotalOrbits(root);

        System.out.println("Orbits: " + orbits);
    }
}
