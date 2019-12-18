package org.advent.dfs;

import org.advent.util.IntPoint;

import java.util.Optional;

public interface DfsI {
    /**
     * Finds the Node that has the position
     * @param position
     * @return
     */
    Optional<DfsNode> findByPosition(IntPoint position);

    /**
     * Chacks the dungeon and returns true if it is possible to go there.
     * @return
     */
    boolean isPositionViable(IntPoint p);

    /**
     * Returns a key if it is at that position, otherwise 0
     * @param p
     * @return
     */
    Optional<Character> keyAtPosition(IntPoint p);
}
