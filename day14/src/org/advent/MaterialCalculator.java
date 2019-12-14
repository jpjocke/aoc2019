package org.advent;

import java.util.ArrayList;
import java.util.List;

public class MaterialCalculator {
    // excess???
    private List<Material> excess;
    private List<Material> materials;
    private List<Reaction> reactions;

    public MaterialCalculator(List<Reaction> reactions) {
        materials = new ArrayList<>();
        excess = new ArrayList<>();
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
                for (int j = excess.size() -1; j >= 0 ; j--) {
                    if (excess.get(j).isSameMaterial(m)) {
                        needed -= excess.get(j).take(needed);
                        System.out.println("### Found " + excess.get(j) + " now need: " + needed);

                        if(excess.get(j).getAmount() == 0) {
                            excess.remove(j);
                        }
                    }
                }
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
