package org.advent.dfs;

import org.advent.Dungeon;
import org.advent.util.IntPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DfsCalc implements DfsI {
    public static boolean DEBUG = false;
    private Map<IntPoint, Character> masterKeys;
    private Map<IntPoint, DfsNode> mappedDfs;
    private Map<IntPoint, CachedDistanceList> distanceMap;
    private Map<String, Integer> visitedMap;
    private Dungeon map;

    public DfsCalc(Map<IntPoint, Character> masterKeys, Map<IntPoint, Character> doors, Dungeon map, IntPoint position) {
        this.masterKeys = masterKeys;
        this.map = map;
        distanceMap = new HashMap<>();
        mappedDfs = new HashMap<>();
        visitedMap = new HashMap<>();
        mappedDfs.put(position, new DfsNode(null, position, 0));
        for (IntPoint key : masterKeys.keySet()) {
            mappedDfs.put(key, new DfsNode(null, key, 0));
        }

        for (IntPoint pos : mappedDfs.keySet()) {
            mappedDfs.get(pos).explore(this);
            CachedDistanceList cdl = new CachedDistanceList();
            for (IntPoint key : masterKeys.keySet()) {
                CachedDistance d = new CachedDistance(pos, key, masterKeys.get(key));
                mappedDfs.get(pos).distanceToo(d, doors);
                cdl.add(d);
            }
            distanceMap.put(pos, cdl);
            if (DEBUG) {
                map.print(position, masterKeys, doors, mappedDfs.get(pos));
            }
        }
    }

    public int exploreForPos(Map<IntPoint, Character> keys, List<Character> doors, IntPoint position) {
        if (keys.isEmpty()) {
            if (DEBUG) {
                System.out.println("No more keys");
            }
            return 0;
        }
        String doorMemory = new String();
        if (masterKeys.containsKey(position)) {
            doorMemory = doorMemory + masterKeys.get(position);
        }
        for(int i = 0; i < doors.size(); i++) {
            doorMemory = doorMemory + doors.get(i);
        }
        if (visitedMap.containsKey(doorMemory)) {
            return visitedMap.get(doorMemory);
        }
        printKeysAndDoors(keys, doors);

        List<CachedDistance> foundDistances = new ArrayList<>();
        int smallestSTep = Integer.MAX_VALUE;
        for (IntPoint key : keys.keySet()) {
            CachedDistanceList cdl = distanceMap.get(position);
            if (cdl.isPositionReachable(key, doors)) {
                CachedDistance d = cdl.get(key);
                if (DEBUG) {
                    System.out.println("Found: " + keys.get(key) + " on p: " + position + ", steps: " + d.getSteps());
                }
                foundDistances.add(d);
                if (smallestSTep > d.getSteps()) {
                    smallestSTep = d.getSteps();
                }
            }
        }

        int lessSteps = Integer.MAX_VALUE;
        for (CachedDistance d : foundDistances) {

            IntPoint nextStart = d.getTo();
            if (d.getSteps() > smallestSTep * 2) {
                if (DEBUG) {
                    System.out.println("Skipping : " + nextStart);
                }
            }


            Map<IntPoint, Character> nextKeys = new HashMap<>(keys);
            List<Character> nextDoors = new ArrayList<>(doors);
            nextKeys.remove(nextStart);
            for (Character door : nextDoors) {
                if (door == keys.get(nextStart) - 32) {
                    if (DEBUG) {
                        System.out.println("Removing door: " + door);
                    }
                    nextDoors.remove(door);
                    break;
                }
            }


            int nextSteps = d.getSteps() + exploreForPos(nextKeys, nextDoors, nextStart);
            if (nextSteps < lessSteps) {
                lessSteps = nextSteps;
                if (DEBUG) {
                    System.out.println("Found shorter way: " + lessSteps);
                }
            }
            if (DEBUG) {
                System.out.println("Shortest way: " + lessSteps);
            }

        }
        visitedMap.put(doorMemory, lessSteps);
        return lessSteps;
    }

    private void printKeysAndDoors(Map<IntPoint, Character> keys, List<Character> doors) {
        if (DEBUG) {
            System.out.println("Exploring---------- ");
            System.out.print("-- Keys: ");
            for (IntPoint key : keys.keySet()) {
                System.out.print(keys.get(key) + " - ");
            }
            System.out.println("-");
            System.out.print("-- Doors: ");
            for (Character door : doors) {
                System.out.print(door + " - ");
            }
            System.out.println("-");
        }
    }

    @Override
    public Optional<DfsNode> findByPosition(IntPoint startingPos, IntPoint searchedPos) {
        return mappedDfs.get(startingPos).findByPosition(searchedPos);
    }

    @Override
    public boolean isPositionViable(IntPoint p) {
        char c = map.getCharAt(p);
        // open space
        return c == '.';
        // wall
    }

    @Override
    public Optional<Character> keyAtPosition(IntPoint p) {
        if (masterKeys.containsKey(p)) {
            return Optional.of(masterKeys.get(p));
        }
        return Optional.empty();
    }
}
