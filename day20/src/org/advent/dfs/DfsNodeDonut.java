package org.advent.dfs;

import org.advent.DonutMaze;
import org.advent.util.IntPoint;

import java.util.Optional;


public class DfsNodeDonut {
    public static boolean DEBUG = false;
    protected IntPoint pos;
    protected int steps;
    protected DfsNodeDonut parent;
    protected DfsNodeDonut up;
    protected DfsNodeDonut down;
    protected DfsNodeDonut left;
    protected DfsNodeDonut right;
    protected DfsNodeDonut teleport;

    public DfsNodeDonut(DfsNodeDonut parent, IntPoint pos, int steps) {
        if (DEBUG) {
            System.out.println("Created: " + pos + " steps: " + steps);
        }
        this.pos = pos;
        this.steps = steps;
        this.parent = parent;
    }

    public IntPoint getPos() {
        return pos;
    }

    public void explore(DfsIDonut dfsi, DonutMaze map) {
        if (DEBUG) {
            System.out.println("Exploring: " + pos + " steps: " + steps);
        }
        if (up == null) {
            IntPoint upP = pos.copy();
            upP.y--;
            up = exploreForPos(dfsi, upP);
        }

        if (down == null) {
            IntPoint upP = pos.copy();
            upP.y++;
            down = exploreForPos(dfsi, upP);
        }

        if (left == null) {
            IntPoint upP = pos.copy();
            upP.x--;
            left = exploreForPos(dfsi, upP);
        }

        if (right == null) {
            IntPoint upP = pos.copy();
            upP.x++;
            right = exploreForPos(dfsi, upP);
        }
        if (DEBUG) {
            map.print(getTop(), pos);
        }

        if (up != null) {
            up.explore(dfsi, map);
        }
        if (down != null) {
            down.explore(dfsi, map);
        }
        if (left != null) {
            left.explore(dfsi, map);
        }
        if (right != null) {
            right.explore(dfsi, map);
        }
        if (teleport != null) {
            teleport.explore(dfsi, map);
        }
    }

    private DfsNodeDonut getTop() {
        if (steps == 0) {
            return this;
        }
        return parent.getTop();
    }

    public void removeFromParent(IntPoint newParentPos) {
        if (parent.up != null && parent.up.getPos().equals(this.pos)) {
            parent.up = null;
        }
        if (parent.down != null && parent.down.getPos().equals(this.pos)) {
            parent.down = null;
        }
        if (parent.left != null && parent.left.getPos().equals(this.pos)) {
            parent.left = null;
        }
        if (parent.right != null && parent.right.getPos().equals(this.pos)) {
            parent.right = null;
        }
        if (parent.teleport != null && parent.teleport.getPos().equals(this.pos)) {
            DfsNodeDonut tmp = parent;
            DfsNodeDonut t = parent.teleport;
            parent.teleport = null;
            parent.removeFromParent(t.pos);
            t.teleport = tmp;
        }

        // also remove children!!
        up = null;
        down = null;
        left = null;
        right = null;
        if (up != null && up.pos.equals(newParentPos)) {
            up = null;
        }
        if (down != null && down.pos.equals(newParentPos)) {
            down = null;
        }
        if (left != null && left.pos.equals(newParentPos)) {
            left = null;
        }
        if (right != null && right.pos.equals(newParentPos)) {
            right = null;
        }
    }

    protected IntPoint getStartPoint() {
        if (steps == 0) {
            return pos;
        }
        return parent.getStartPoint();
    }

    public DfsNodeDonut exploreForPos(DfsIDonut dfsi, IntPoint p) {
        // check map if we can go here
        if (dfsi.isPositionViable(p)) {

            // check if we already have been here
            Optional<DfsNodeDonut> optionalDfsNode = dfsi.findByPosition(p);

            if (optionalDfsNode.isPresent()) {
                return takeNodeIfCloser(p, optionalDfsNode.get());
            } else {

                Optional<IntPoint> teleport = dfsi.findTeleport(p);
                if (teleport.isPresent()) {
                    IntPoint exit = teleport.get();
                    if (DEBUG) {
                        System.out.println("teleport to: " + exit);
                    }
                    DfsNodeDonut d = new DfsNodeDonut(this, p, steps + 1);
                    // have we been at the teleport?
                    Optional<DfsNodeDonut> optionalExit = dfsi.findByPosition(exit);
                    if (optionalExit.isPresent()) {
                        DfsNodeDonut node = takeNodeIfCloser(p, optionalDfsNode.get());
                        if (node != null) {
                            d.teleport = node;
                        }
                    } else {
                        if (DEBUG) {
                            System.out.println("New teleport");
                        }
                        DfsNodeDonut t = new DfsNodeDonut(d, teleport.get(), steps + 2);
                        d.teleport = t;
                    }

                    return d;
                } else {
                    // we have not been here
                    return new DfsNodeDonut(this, p, steps + 1);

                }
            }
        }
        return null;
    }

    private DfsNodeDonut takeNodeIfCloser(IntPoint p, DfsNodeDonut node) {
        if (DEBUG) {
            System.out.println("Already explored: " + p + " had steps: " + node.getSteps());
        }
        // check if it is closer this way
        if (node.getSteps() > steps + 1) {
            if (DEBUG) {
                System.out.println("Stealing dfs at: " + p);
            }
            node.removeFromParent(p);
            node.setSteps(steps + 1);
            return node;
        } else {
            // old way was closer, skip this
            return null;
        }
    }

    public Optional<DfsNodeDonut> findByPosition(IntPoint p) {
        if (pos.equals(p)) {
            return Optional.of(this);
        }
        Optional<DfsNodeDonut> child = Optional.empty();
        if (up != null) {
            child = up.findByPosition(p);
        }
        if (down != null && !child.isPresent()) {
            child = down.findByPosition(p);
        }
        if (left != null && !child.isPresent()) {
            child = left.findByPosition(p);
        }
        if (right != null && !child.isPresent()) {
            child = right.findByPosition(p);
        }
        if (teleport != null && !child.isPresent()) {
            child = teleport.findByPosition(p);
        }

        return child;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
        int childSteps = steps + 1;

        if (up != null) {
            up.setSteps(childSteps);
        }
        if (down != null) {
            down.setSteps(childSteps);
        }
        if (left != null) {
            left.setSteps(childSteps);
        }
        if (right != null) {
            right.setSteps(childSteps);
        }
        if (teleport != null) {
            teleport.setSteps(childSteps);
        }
    }
}
