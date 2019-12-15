package org.advent;

import org.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialCalculator {
    public static boolean DEBUG = false;
    private Excess excess;
    private List<Material> materials;
    private List<Reaction> reactions;

    public MaterialCalculator(List<Reaction> reactions) {
        materials = new ArrayList<>();
        excess = new Excess();
        this.reactions = reactions;
    }

    public long oreNeededForOneFuelTwo(long fuel) {
        long total = solveForMaterial(new Material(fuel, "FUEL"), true);
        System.out.println("-- Excess: " + excess);
        return total;
    }

    public long oreNeededForOneFuelTwo() {
        return oreNeededForOneFuelTwo(1);
    }


    private long solveForMaterial(Material m, boolean useExcess) {
        if (DEBUG) {
            System.out.println("-- Solving for: " + m + ", useExcess: " + useExcess);
        }
        if (m.isOre()) {
            return m.getAmount();
        }

        long total = 0;

        List<Reaction> reactionsForMaterial = reactions.stream()
                .filter(r -> r.getOutput().isSameMaterial(m))
                .collect(Collectors.toList());

        if (reactionsForMaterial.size() == 1) {
            Reaction r = reactionsForMaterial.get(0);
            long needed = m.getAmount();
            if (useExcess) {
                needed -= excess.takeExcess(needed, m.getName());

            }
            // TODO effektivisera denna!! annras tar det för lång tid att hitta stora tal
            long t = r.getOutput().getAmount();
            long times = needed / t;
            if(needed != times * t) {
                times++;
            }
/*
            times = 1;
            while (r.getOutput().getAmount() * times < needed) {
                times++;
            }
*/
            if (DEBUG) {

                System.out.println("Using " + times + "x reaction: " + r);
            }
            long created = r.getOutput().getAmount() * times;

            if (needed != 0) {
                if (created > needed) {

                    if (DEBUG) {

                        System.out.println("### EXCESS: " + created + " created but " + needed + " " + m.getName() + " needed!");
                    }
                    if (useExcess) {
                        excess.add(new Material(created - needed, m.getName()));
                    }
                }

                for (int i = 0; i < r.getInput().size(); i++) {
                    Material next = r.getInput().get(i);
                    total += solveForMaterial(new Material(next.getAmount() * times, next.getName()), useExcess);
                }
            }
        } else {
            throw new RuntimeException("MANY reactions " + reactionsForMaterial.size());
        }

        if (DEBUG) {
            System.out.println("-- Needed " + total + " for " + m);
            System.out.println("-- Excess " + excess);
            System.out.println("--------------------------------------------------------");
        }
        return total;
    }

}
