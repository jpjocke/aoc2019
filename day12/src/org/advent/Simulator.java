package org.advent;

import java.util.List;

public class Simulator {
    List<Moon> moons;

    public Simulator(List<Moon> moons) {
        this.moons = moons;
    }

    public void simulateOnce() {
        System.out.println("-------------------");
        for (int i = 0; i < moons.size(); i++) {
            Moon a = moons.get(i);
            for (int j = 0; j < moons.size(); j++) {
                if (i == j) {
                    continue;
                }
                a.applyMoonGravityOnVelocity(moons.get(j));
            }
        }

        for (int i = 0; i < moons.size(); i++) {
            Moon a = moons.get(i);
            a.applyVelocity();
            System.out.println(a);
        }

        int totalEnergy = 0;
        for (int i = 0; i < moons.size(); i++) {
            Moon a = moons.get(i);
            int pot = a.potentialEnergy();
            int kin = a.kineticEnergy();
            int e = pot * kin;
            System.out.println("Energy : " + e);
            totalEnergy += e;
        }
        System.out.println("TotalEnergy : " + totalEnergy);
    }
}
