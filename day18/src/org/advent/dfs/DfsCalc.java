package org.advent.dfs;

import org.advent.Dungeon;
import org.advent.util.IntPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DfsCalc implements DfsI {
    public static boolean DEBUG = false;
    private Map<IntPoint, Character> keys;
    private Map<IntPoint, Character> doors;
    private Dungeon map;
    private IntPoint position;
    private DfsNode top;

    public DfsCalc(Map<IntPoint, Character> keys, Map<IntPoint, Character> doors, Dungeon map, IntPoint position) {
        this.keys = keys;
        this.doors = doors;
        this.map = map;
        this.position = position;
        top = new DfsNode(null, position, 0);
    }

    public int explore(int iteration) {
        if (keys.isEmpty()) {
            if (DEBUG) {
                System.out.println("No more keys, iteration: " + iteration);
            }
            return 0;
        }
        printKeysAndDoors(iteration);
        top.explore(this);
        //  map.print(position, keys, doors, top);
        int lessSteps = Integer.MAX_VALUE;
        for (IntPoint key : keys.keySet()) {
            Optional<DfsNode> oNode = top.findByPosition(key);
            if (oNode.isPresent()) {
                DfsNode node = oNode.get();
                IntPoint nextPos = node.getPos();
                if (DEBUG) {
                    System.out.println("Found: " + keys.get(key) + " on p: " + nextPos + ", steps: " + node.getSteps() + ", iteration: " + iteration);
                }

                DfsCalc next = buildNextIteration(key);
                int nextSteps = node.getSteps() + next.explore(iteration + 1);
                if (nextSteps < lessSteps) {
                    lessSteps = nextSteps;
                    if (DEBUG) {
                        System.out.println("Found shorter way: " + lessSteps);
                    }
                }
                if (DEBUG) {
                    System.out.println("Shortest way: " + lessSteps + ", iteration: " + iteration);
                }

            }
        }

        return lessSteps;
    }

    private void printKeysAndDoors(int iteration) {
        System.out.println("Exploring---------- iteration: " + iteration);
        if (DEBUG) {
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
    public Optional<DfsNode> findByPosition(IntPoint p) {
        return top.findByPosition(p);
    }

    @Override
    public boolean isPositionViable(IntPoint p) {
        if (doors.containsKey(p)) {
            // Door is in the way
            return false;
        }
        char c = map.getCharAt(p);
        if (c == '.') {
            // open space
            return true;
        }
        // wall
        return false;
    }

    @Override
    public Optional<Character> keyAtPosition(IntPoint p) {
        if (keys.containsKey(p)) {
            return Optional.of(keys.get(p));
        }
        return Optional.empty();
    }

    private DfsCalc buildNextIteration(IntPoint key) {
        Map<IntPoint, Character> nextKeys = new HashMap<>(keys);
        Map<IntPoint, Character> nextDoors = new HashMap<>(doors);
        nextKeys.remove(key);
        for (IntPoint door : nextDoors.keySet()) {
            if (nextDoors.get(door) == keys.get(key) - 32) {
                if (DEBUG) {
                    System.out.println("Removing door: " + nextDoors.get(door));
                }
                nextDoors.remove(door);
                break;
            }
        }
        return new DfsCalc(nextKeys, nextDoors, map, key);
    }
}
