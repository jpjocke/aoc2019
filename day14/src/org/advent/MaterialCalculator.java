package org.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialCalculator {
    private Excess excess;
    private List<Material> materials;
    private List<Reaction> reactions;

    public MaterialCalculator(List<Reaction> reactions) {
        materials = new ArrayList<>();
        excess = new Excess();
        this.reactions = reactions;
    }

    public int oreNeededForOneFuel() {
        materials.addAll(materialsForFuel());

        int iterations = 0;
        while (!convertOneStep()) {
            iterations++;
            System.out.println("-- Materials: " + materials);
            System.out.println("-- Excess: " + excess);
            System.out.println("--------------- iterations: " + iterations);
        }


        System.out.println("-- ORE");
        toOre();
        return materials.get(0).getAmount();
    }


    private List<Material> materialsForFuel() {
        for (int i = 0; i < reactions.size(); i++) {
            if (reactions.get(i).getOutput().isFuel()) {
                System.out.println("FUEL: " + reactions.get(i));
                return reactions.get(i).getInput();
            }
        }
        return null;
    }

    public int oreNeededForOneFuelTwo() {
        int total = solveForMaterial(new Material(1, "FUEL"), true);
        System.out.println("-- Excess: " + excess);
        return total;
    }


    private int solveForMaterial(Material m, boolean useExcess) {
        System.out.println("-- Solving for: " + m + ", useExcess: " + useExcess);
        if (m.isOre()) {
            return m.getAmount();
        }

        int total = 0;

        List<Reaction> reactionsForMaterial = reactions.stream()
                .filter(r -> r.getOutput().isSameMaterial(m))
                .collect(Collectors.toList());

        if (reactionsForMaterial.size() == 1) {
            Reaction r = reactionsForMaterial.get(0);
            int needed = m.getAmount();
            if (useExcess) {
                needed -= excess.takeExcess(needed, m.getName());

            }
            int times = 1;
            while (r.getOutput().getAmount() * times < needed) {
                times++;
            }
            System.out.println("Using " + times + "x reaction: " + r);
            int created = r.getOutput().getAmount() * times;

            if (needed != 0) {
                if (created > needed) {
                    System.out.println("### EXCESS: " + created + " created but " + needed + " " + m.getName() + " needed!");
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

        System.out.println("-- Needed " + total + " for " + m);
        System.out.println("-- Excess " + excess);
        System.out.println("--------------------------------------------------------");
        return total;
    }


    private boolean convertOneStep() {
        for (int i = materials.size() - 1; i >= 0; i--) {
            Material m = materials.get(i);
            if (isInputOre(m)) {
                continue;
            }
            convertMaterialThroughReaction(m);
            materials.remove(i);
        }
        reduceMaterials();

        for (int i = 0; i < materials.size(); i++) {
            Material m = materials.get(i);

            if (!isInputOre(m)) {
                return false;
            }
        }

        // we only have materials needed from ore left
        return true;
    }

    private boolean isInputOre(Material m) {
        for (int i = 0; i < reactions.size(); i++) {
            Reaction r = reactions.get(i);
            if (r.getOutput().isSameMaterial(m)) {
                if (r.getInput().size() == 1 && r.getInput().get(0).isOre()) {
                    return true;
                }
            }
        }

        return false;
    }

    private void convertMaterialThroughReaction(Material m) {
        System.out.println("Converting: " + m);
        for (int i = 0; i < reactions.size(); i++) {
            Reaction r = reactions.get(i);
            Material out = r.getOutput();
            if (out.isSameMaterial(m)) {
                // TODO -> ta ur excess först
                int needed = m.getAmount();
                System.out.println("Need " + needed);
                needed -= excess.takeExcess(needed, m.getName());
                int it = 1;
                while (out.getAmount() * it < needed) {
                    it++;
                }
                System.out.println("Using " + it + "x reaction: " + r);
                int created = out.getAmount() * it;

                if (created > needed) {
                    System.out.println("### EXCESS: " + created + " created but " + needed + " " + m.getName() + " needed!");
                    excess.add(new Material(created - needed, m.getName()));
                }

                int finalIt = it;
                r.getInput().stream().forEach(ma -> materials.add(ma.copy(finalIt)));
                System.out.println("-- Materials: " + materials);
            }
        }
    }

    private void reduceMaterials() {
        System.out.println("Reducing: " + materials);
        for (int i = materials.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (materials.get(j).add(materials.get(i))) {
                    System.out.println("Added " + materials.get(i) + " to " + materials.get(j));
                    materials.remove(i);
                    break;
                }
            }
        }
        System.out.println("Reduce result: " + materials);
    }

    private void toOre() {
        for (int i = materials.size() - 1; i >= 0; i--) {
            Material m = materials.get(i);
            convertMaterialThroughReaction(m);
            materials.remove(i);
        }
        reduceMaterials();
    }
}
