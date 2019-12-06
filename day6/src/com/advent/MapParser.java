package com.advent;

import java.util.List;

public class MapParser {
    public static final String ROOT = "COM";
    private List<String> map;
    private Boolean[] found;

    public MapParser(List<String> map) {
        this.map = map;
        found = new Boolean[map.size()];
        for (int i = 0; i < found.length; i++) {
            found[i] = false;
        }
    }

    public SpaceObject parseMap() {
        SpaceObject current = findCOM();

        resolveChildrenRecursive(current);
        return current;
    }

    private SpaceObject findCOM() {
        for (int i = 0; i < map.size(); i++) {
            String[] split = split(map.get(i));
            if (split[0].equals(ROOT)) {
             //   found[i] = true;
                return new SpaceObject(split[0]);
            }
        }
        return null;
    }

    private void resolveChildrenRecursive(SpaceObject parent) {
        for (int i = 0; i < map.size(); i++) {
            if (found[i]) {
                continue;
            }
            String[] split = split(map.get(i));
            if (split[0].equals(parent.getId())) {
                found[i] = true;
                SpaceObject child  = new SpaceObject(split[1]);
                parent.add(child);
                resolveChildrenRecursive(child);
            }

        }
    }

    private String[] split(String mapObject) {
        return mapObject.split("\\)");
    }
}
