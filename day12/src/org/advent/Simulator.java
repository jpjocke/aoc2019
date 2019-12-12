package org.advent;

import org.advent.util.IntVector;
import org.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulator {
    public static boolean DEBUG = false;
    List<Moon> moons;
    int maxSimSave = 200000000;

    public Simulator(List<Moon> moons) {
        this.moons = moons;
    }

    public long simulateUntilStateReached(boolean brute) {
        long simulations = 0;

        if (brute) {
            BruteCalc b = new BruteCalc();
            simulations = b.brute();
        } else {
            LcmCalc lcmCalc = new LcmCalc();
            simulations = lcmCalc.doLcm();
        }
        return simulations;
    }

    private byte[] posAll() {
        byte[] pos = new byte[moons.size() * 6];
        for (int i = 0; i < moons.size(); i++) {
            int index = i * 6;
            pos[index] = (byte) moons.get(i).position.x;
            pos[index + 1] = (byte) moons.get(i).position.y;
            pos[index + 2] = (byte) moons.get(i).position.z;

            pos[index + 3] = (byte) moons.get(i).velocity.x;
            pos[index + 4] = (byte) moons.get(i).velocity.y;
            pos[index + 5] = (byte) moons.get(i).velocity.z;
        }

        return pos;
    }

    private void reset(byte[] start) {
        moons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int index = i * 6;
            IntVector pos = new IntVector(start[index], start[index + 1], start[index + 2]);
            Moon m = new Moon(pos);
            moons.add(m);
        }
    }

    public void simulateOnce() {
        if (DEBUG) {
            System.out.println("-------------------");

        }
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
            if (DEBUG) {
                System.out.println(a);
            }
        }

        int totalEnergy = 0;
        for (int i = 0; i < moons.size(); i++) {
            Moon a = moons.get(i);
            int pot = a.potentialEnergy();
            int kin = a.kineticEnergy();
            int e = pot * kin;
            totalEnergy += e;
        }
        if (DEBUG) {
            System.out.println("TotalEnergy : " + totalEnergy);

        }
    }

    class BruteCalc {
        byte[][] history;

        public BruteCalc() {
            history = new byte[maxSimSave][];
        }

        public long brute() {
            long simulations = 0;
            byte[] pos = posAll();
            while (!haveWeBeenHereBefore(pos, simulations)) {
                simulateOnce();
                simulations++;
                if (simulations % 100000 == 0) {
                    System.out.println("simulations: " + simulations);
                }
                pos = posAll();
                if (simulations > history.length * 3) {
                    System.out.println("Too many iterations....");
                    break;
                }
            }
            return simulations;
        }

        private boolean haveWeBeenHereBefore(byte[] pos, long simulations) {
            long min = Math.min(history.length, simulations);
            outerLoop:
            for (int i = 0; i < min; i++) {
                for (int j = 0; j < history[i].length; j++) {
                    if (pos[j] != history[i][j]) {
                        break outerLoop;
                    }
                }
                return true;
            }
            if (simulations < history.length) {
                history[(int) simulations] = pos;
            }
            return false;
        }
    }

    class LcmCalc {
        byte[][] history;

        public LcmCalc() {
            history = new byte[maxSimSave][];
            /*
            for (int i = 0; i < history.length; i++) {
                history[i] = new byte[6];
            }

             */
        }

        public long doLcm() {
            byte[] startAll = posAll();
            long[] individualCycles = new long[]{0, 0, 0, 0};
            long[] individualCyclesReturn = new long[]{0, 0, 0, 0};
            for (int i = 0; i < moons.size(); i++) {
                Moon m = moons.get(i);
                byte[] pos = pos(m);
                individualCyclesReturn[i] = haveWeBeenHereBeforeSingle(individualCycles[i], pos);
                while (individualCyclesReturn[i] == -1) {
                    simulateOnce();
                    individualCycles[i]++;

                    if (individualCycles[i] % 100000 == 0) {
                        System.out.println("i: " + i + ", simulations: " + individualCycles[i]);
                    }

                    pos = pos(m);
                    individualCyclesReturn[i] = haveWeBeenHereBeforeSingle(individualCycles[i], pos);

                    if (individualCyclesReturn[i] > history.length * 3) {
                        System.out.println("Too many iterations....");
                        break;
                    }
                }

                reset(startAll);
            }
            Arrays.stream(individualCyclesReturn).forEach(l -> System.out.println(l));
            return findLcm(individualCyclesReturn);
        }

        private long haveWeBeenHereBeforeSingle(long simulations, byte[] pos) {
            long min = Math.min(history.length, simulations - 1);
            outerLoop:
            for (int i = 0; i < min; i++) {
                for (int j = 0; j < history[i].length; j++) {
                    if (pos[j] != history[i][j]) {
                        break outerLoop;
                    }
                }
                return simulations - i;
            }
            if (simulations < history.length) {
               history[(int)simulations] = pos;
            }
            return -1;
        }

        private long findLcm(long[] cycles) {
            long lcm1 = Util.lcm(cycles[0], cycles[1]);
            long lcm2 = Util.lcm(cycles[2], cycles[3]);
            return Util.lcm(lcm1, lcm2);
        }

        private byte[] pos(Moon m) {
            return new byte[]{(byte) m.position.x,
                    (byte) m.position.y,
                    (byte) m.position.z,
                    (byte) m.velocity.x,
                    (byte) m.velocity.y,
                    (byte) m.velocity.z};
        }
    }
}
