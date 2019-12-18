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
    private Map<IntPoint, Character> keys;
    private Map<IntPoint, DfsNode> mappedDfs;
    private Dungeon map;
    private IntPoint position;

    public DfsCalc(Map<IntPoint, Character> keys, Map<IntPoint, Character> doors, Dungeon map, IntPoint position) {
        this.keys = keys;
        this.map = map;
        this.position = position;
        mappedDfs = new HashMap<>();
        mappedDfs.put(position, new DfsNode(null, position, 0));
        for (IntPoint key : keys.keySet()) {
            mappedDfs.put(key, new DfsNode(null, key, 0));
        }

        for (IntPoint pos : mappedDfs.keySet()) {
            mappedDfs.get(pos).explore(this);
            if (DEBUG) {
                map.print(position, keys, doors, mappedDfs.get(pos));
            }
        }
    }

    public int exploreForPos(Map<IntPoint, Character> keys, Map<IntPoint, Character> doors, IntPoint position) {
        if (keys.isEmpty()) {
            if (DEBUG) {
                System.out.println("No more keys");
            }
            return 0;
        }
        printKeysAndDoors(keys, doors);

        List<DfsNode> foundNodes = new ArrayList<>();
        int smallestSTep = Integer.MAX_VALUE;
        for (IntPoint key : keys.keySet()) {
            Optional<DfsNode> oNode = mappedDfs.get(position).findPosStopAtDoors(key, keys, doors);
            if (oNode.isPresent()) {
                DfsNode node = oNode.get();
                foundNodes.add(node);
                if (DEBUG) {
                    System.out.println("Found: " + keys.get(node.getPos()) + " on p: " + position + ", steps: " + node.getSteps());
                }
                if(smallestSTep > node.getSteps()) {
                    smallestSTep = node.getSteps();
                }
            }
        }

        int lessSteps = Integer.MAX_VALUE;
        for (DfsNode node : foundNodes) {
            if(node.getSteps() > smallestSTep * 2) {
                if (DEBUG) {
                    System.out.println("Skipping : " + node.getPos());
                }
            }


            Map<IntPoint, Character> nextKeys = new HashMap<>(keys);
            Map<IntPoint, Character> nextDoors = new HashMap<>(doors);
            nextKeys.remove(node.getPos());
            for (IntPoint door : nextDoors.keySet()) {
                if (nextDoors.get(door) == keys.get(node.getPos()) - 32) {
                    if (DEBUG) {
                        System.out.println("Removing door: " + nextDoors.get(door));
                    }
                    nextDoors.remove(door);
                    break;
                }
            }

            int nextSteps = node.getSteps() + exploreForPos(nextKeys, nextDoors, node.getPos());
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
        return lessSteps;
    }

    private void printKeysAndDoors(Map<IntPoint, Character> keys, Map<IntPoint, Character> doors) {
        if (DEBUG) {
            System.out.println("Exploring---------- ");
            System.out.print("-- Keys: ");
            for (IntPoint key : keys.keySet()) {
                System.out.print(keys.get(key) + " - ");
            }
            System.out.println("-");
            System.out.print("-- Doors: ");
            for (IntPoint key : doors.keySet()) {
                System.out.print(doors.get(key) + " - ");
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
        if (keys.containsKey(p)) {
            return Optional.of(keys.get(p));
        }
        return Optional.empty();
    }
}
