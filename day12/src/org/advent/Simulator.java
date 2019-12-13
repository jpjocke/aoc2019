package org.advent;

import org.advent.util.Util;

import java.math.BigInteger;
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
    }

    class LcmCalc {
        MoonHistory mX;
        MoonHistory mY;
        MoonHistory mZ;

        private void setup() {
            mX = new MoonHistory(1);
            mY = new MoonHistory(2);
            mZ = new MoonHistory(3);
        }

        public BigInteger doLcm() {
            setup();

            long simulations = 0;

            while (true) {
                if (!mX.found) {
                    mX.haveWeBeenHereBefore(simulations, moons);
                }
                if (!mY.found) {
                    mY.haveWeBeenHereBefore(simulations, moons);
                }
                if (!mZ.found) {
                    mZ.haveWeBeenHereBefore(simulations, moons);
                }

                if (mX.found && mY.found && mZ.found) {
                    break;
                }

                simulateOnce();
                simulations++;
                if (simulations % 1000 == 0) {
                    System.out.println("simulations: " + simulations);
                }
            }
            BigInteger r = lcmHistory(mX.cycles, mY.cycles, mZ.cycles);
            System.out.println(r);
            return r;
        }

        private BigInteger lcmHistory(long x, long y, long z) {
            System.out.println("Moonz--------------");
            System.out.println("x: " + x + ", y: " + y + ", z: " + z);

            BigInteger lcm1 = Util.lcm2(x, y);
            System.out.println("lcm1: " + lcm1);
            BigInteger lcm2 = Util.lcm3(lcm1, BigInteger.valueOf(z));
            System.out.println("lcm2: " + lcm2);
            return lcm2;
        }
    }
}
