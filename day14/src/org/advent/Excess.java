package org.advent;

import java.util.ArrayList;
import java.util.List;

public class Excess {
    public static boolean DEBUG = false;
    private List<Material> excess;

    public Excess() {
        excess = new ArrayList<>();
    }

    public void add(Material m) {
        if (DEBUG) {
            System.out.println("// Adding excess:" + m);

        }
        boolean added = false;
        for (int i = 0; i < excess.size(); i++) {
            if (excess.get(i).add(m)) {
                added = true;
                break;
            }
        }
        if (!added) {
            excess.add(m);
        }
    }

    public long takeExcess(long needed, String name) {
        long taken = 0;
        for (int j = excess.size() - 1; j >= 0; j--) {
            if (excess.get(j).getName().equals(name)) {
                taken = excess.get(j).take(needed);
                if (DEBUG) {

                    System.out.println("// Found " + excess.get(j) + " now need: " + needed);
                }

                if (excess.get(j).getAmount() == 0) {
                    excess.remove(j);
                }
            }
        }
        return taken;
    }

    @Override
    public String toString() {
        return "Excess{" +
                excess +
                '}';
    }
}
