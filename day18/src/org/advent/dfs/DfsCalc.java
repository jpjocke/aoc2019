package org.advent.dfs;

import org.advent.Dungeon;
import org.advent.util.IntPoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DfsCalc implements DfsI {
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

    public void explore() {
        top.explore(this);
        map.print(position, keys, doors, top);
    }

    @Override
    public Optional<DfsNode> findByPosition(IntPoint p) {
        return top.findByPosition(p);
    }

    @Override
    public boolean isPositionViable(IntPoint p) {
     //   map.print(p, keys, doors);
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
}
