package org.advent.dfsstep2.dfs;

import org.advent.DonutMaze;
import org.advent.util.IntPoint;

import java.util.Map;
import java.util.Optional;

public class DfsCalcDonut2 implements DfsIDonut2 {

    public static boolean DEBUG = false;
    private Map<IntPoint, String> teleports;
    private DonutMazeStep2 map;
    private IntPoint start;
    private DfsNodeDonut2 top;
    private int levels;

    public DfsCalcDonut2(Map<IntPoint, String> teleports, DonutMazeStep2 map, int levels) {
        this.teleports = teleports;
        this.map = map;
        for (IntPoint p : teleports.keySet()) {
            if (teleports.get(p).equals("AA")) {
                start = p;
                break;
            }
        }
        top = new DfsNodeDonut2(null, start, 0, 0);
        this.levels = levels;
        top.DEBUG = true;
    }

    public int exploreFromStart() {
        top.explore(this, map);
        map.printFull(top, start, levels);

        Optional<DfsNodeDonut2> exit = findByPosition(findExitPos(), 0);
        if (exit.isPresent()) {
            return exit.get().getSteps();
        }

        return 0;
    }


    @Override
    public Optional<DfsNodeDonut2> findByPosition(IntPoint searchedPos, int level) {
        return top.findByPosition(searchedPos, level);
    }

    @Override
    public boolean isPositionViable(IntPoint p) {
        char c = map.getCharAt(p);
        // open space
        return c == '.';
        // wall
    }

    @Override
    public Optional<IntPoint> findTeleport(IntPoint pos, int level) {
        if(level < 0 || level >= levels) {
            return Optional.empty();
        }
        String key = teleports.get(pos);
        if (key != null) {
            if (key.equals("AA") || key.equals("ZZ")) {
                return Optional.empty();

            }
            for (IntPoint exit : teleports.keySet()) {
                if(exit.equals(pos)) {
                    continue;
                }
                if (teleports.get(exit).equals(key)) {
                    return Optional.of(exit);
                }
            }
        }
        return Optional.empty();
    }


    private IntPoint findExitPos() {
        for (IntPoint exit : teleports.keySet()) {
            if (teleports.get(exit).equals("ZZ")) {
                return exit;
            }
        }
        return null;
    }


}
