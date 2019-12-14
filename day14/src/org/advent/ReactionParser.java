package org.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReactionParser {

    public static List<Reaction> parse(String input) {
        String[] rows = input.split("\n");
        return parse(Arrays.asList(rows));
    }

    public static List<Reaction> parse(List<String> input) {

        List<Reaction> reactions = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String[] split = input.get(i).split("=>");
            List<Material> in = parseInput(split[0]);
            Material out = parseMaterial(split[1]);
            reactions.add(new Reaction(in, out));
        }
        return reactions;
    }

    private static List<Material> parseInput(String left) {
        List<Material> materials = new ArrayList<>();
        String[] s = left.split(",");

        for (int i = 0; i < s.length; i++) {
            materials.add(parseMaterial(s[i]));
        }
        return materials;
    }

    private static Material parseMaterial(String right) {
        right = right.trim();
        String[] s = right.split(" ");
        int amount = Integer.parseInt(s[0]);
        Material m = new Material(amount, s[1]);
        return m;
    }
}
