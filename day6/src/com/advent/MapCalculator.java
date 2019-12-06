package com.advent;

public class MapCalculator {

    public static int calculateTotalOrbits(SpaceObject root) {

        return countOrbitsRecursive(root, 0);
    }

    private static int countOrbitsRecursive(SpaceObject spaceObject, int level) {
        int childCount = 0;
        for (int i = 0; i < spaceObject.getChildren().size(); i++) {
            SpaceObject child = spaceObject.getChildren().get(i);
            childCount += countOrbitsRecursive(child, level + 1);
        }
        return level + childCount;
    }

    public static int calculateJumps(SpaceObject root, String from, String to) {

        SpaceObject san = findItem(root, from);
        SpaceObject you = findItem(root, to);
        SpaceObject closestParent = findClosestParent(san, you);
        return distanceToParent(san, closestParent) + distanceToParent(you, closestParent);
    }

    private static SpaceObject findItem(SpaceObject spaceObject, String from) {
        SpaceObject found = null;
        for (int i = 0; i < spaceObject.getChildren().size(); i++) {
            if (found != null) {
                break;
            }
            SpaceObject child = spaceObject.getChildren().get(i);
            if (child.getId().equals(from)) {
                found = child;
            } else {
                found = findItem(child, from);
            }
        }
        return found;
    }

    private static SpaceObject findClosestParent(SpaceObject from, SpaceObject to) {
        SpaceObject parentFrom = from;
        SpaceObject parentTo = to.getParent();

        while (parentTo.getParent() != null) {
            if (parentTo == parentFrom) {
                return parentFrom;
            }
            parentTo = parentTo.getParent();
        }

        return findClosestParent(parentFrom.getParent(), to);
    }

    private static int distanceToParent(SpaceObject from, SpaceObject parent) {
        int count = 0;
        while (from.getParent() != null) {
            if (from.getParent() == parent) {
                break;
            }
            count++;
            from = from.getParent();
        }
        return count;
    }
}
