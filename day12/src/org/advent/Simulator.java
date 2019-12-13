package org.advent;

import org.advent.util.IntVector;
import org.advent.util.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulator {
    public static boolean DEBUG = false;
    List<Moon> moons;
    int maxSimSave = 10000;

    public Simulator(List<Moon> moons) {
        this.moons = moons;
    }

    public BigInteger simulateUntilStateReached(boolean brute) {
        BigInteger simulations;

        if (brute) {
            BruteCalc b = new BruteCalc();
            simulations = BigInteger.valueOf(b.brute());
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
        for (int i = 0; i < 4; i++) {
            int index = i * 6;
            Moon m = moons.get(i);
            m.position.x = start[index];
            m.position.y = start[index + 1];
            m.position.z = start[index + 2];
            m.velocity.x = 0;
            m.velocity.y = 0;
            m.velocity.z = 0;
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
/*
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
 */
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
        List<History> h;

        private void setup(Moon m) {
            h = new ArrayList<>();
            h.add(new History(1, m));
            h.add(new History(2, m));
            h.add(new History(3, m));
        }

        public BigInteger doLcm() {
            byte[] startAll = posAll();
            BigInteger[] moonCyclesTotal = new BigInteger[4];

            for (int i = 0; i < moons.size(); i++) {
                Moon m = moons.get(i);
                setup(m);
                System.out.println("running moon: " + i);
                moons.stream().forEach(mo -> System.out.println(mo));
                long simulations = 0;
                while (!beenHere(simulations, m)) {
                    simulateOnce();
                    simulations++;
                }
                moonCyclesTotal[i] = lcmInMoon();
                reset(startAll);
            }
            System.out.println("Gathered--------------");
            Arrays.stream(moonCyclesTotal).forEach(l -> System.out.println(l));
            BigInteger r = findLcm(moonCyclesTotal);
            System.out.println(r);
            return r;
        }

        private boolean beenHere(long simulations, Moon m) {
            h.stream()
                    .filter(hs -> !hs.found)
                    .forEach(hs -> hs.haveWeBeenHereBeforeSingle(simulations, m));
            return h.stream().allMatch(hs -> hs.found);
        }

        private BigInteger lcmInMoon() {
            System.out.println("Moonz--------------");
            h.stream().forEach(h -> System.out.println(h.cycles));

            BigInteger lcm1 = Util.lcm2(h.get(0).cycles, h.get(1).cycles);
            System.out.println("lcm1: " + lcm1);
            BigInteger lcm2 = Util.lcm3(lcm1, BigInteger.valueOf(h.get(2).cycles));
            System.out.println("lcm2: " + lcm2);
            return lcm2;
        }

        private BigInteger findLcm(BigInteger[] cycles) {
            BigInteger lcm1 = Util.lcm3(cycles[0], cycles[1]);
            BigInteger lcm2 = Util.lcm3(cycles[2], cycles[3]);
            return Util.lcm3(lcm1, lcm2);
        }

        class History {
            int[] start;
            boolean found = false;
            long cycles = -1;
            int mode;

            public History(int mode, Moon m) {
                this.mode = mode;
                start = toPos(m);
            }

            private void haveWeBeenHereBeforeSingle(long simulations, Moon m) {
                if (simulations == 0) {
                    start = toPos(m);
                    return;
                }

                int[] now = toPos(m);

                if (now[0] == start[0] && now[1] == start[1]) {
                    found = true;
                    System.out.println("mode: " + mode + ", sim: " + simulations + ", val: " + start[0] + "," + start[1]);
                    cycles = simulations;
                }
            }

            int[] toPos(Moon m) {
                int[] pos;
                if (mode == 1) {
                    pos = new int[]{ m.position.x, m.velocity.x};
                } else if (mode == 2) {
                    pos = new int[]{ m.position.y, m.velocity.y};
                } else {
                    pos = new int[]{m.position.z, m.velocity.z};
                }
                return pos;
            }
        }
    }
}
