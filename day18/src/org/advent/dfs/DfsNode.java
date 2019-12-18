package org.advent.dfs;

import org.advent.util.IntPoint;

import java.util.Optional;


public class DfsNode {
    private IntPoint pos;
    private int steps;
    private DfsNode up;
    private DfsNode down;
    private DfsNode left;
    private DfsNode right;
    private boolean done;
    private char key;

    public DfsNode(IntPoint pos, int steps) {
        this.pos = pos;
        this.steps = steps;
        done = false;
    }

    public IntPoint getPos() {
        return pos;
    }

    public void explore(DfsI dfsi) {
        if(pos.x == 39 && pos.y == 35) {
            System.out.println("CRASH: " + pos);

        }
        if (!done) {
            System.out.println("Exploring: " + pos);
            IntPoint upP = pos.copy();
            upP.y--;
            up = exploreForPos(dfsi, upP);

            upP = pos.copy();
            upP.y++;
            down = exploreForPos(dfsi, upP);

            upP = pos.copy();
            upP.x--;
            left = exploreForPos(dfsi, upP);

            upP = pos.copy();
            upP.x++;
            right = exploreForPos(dfsi, upP);
        }


        done = true;

        if (up != null) {
            up.explore(dfsi);
        }
        if (down != null) {
            down.explore(dfsi);
        }
        if (left != null) {
            left.explore(dfsi);
        }
        if (right != null) {
            right.explore(dfsi);
        }
    }

    public DfsNode takeChildAtPos(IntPoint p) {
        DfsNode tmp = null;
        if (up != null && up.getPos().equals(p)) {
            tmp = up;
            up = null;
        }
        if (down != null && down.getPos().equals(p)) {
            tmp = down;
            down = null;
        }
        if (left != null && left.getPos().equals(p)) {
            tmp = left;
            left = null;
        }
        if (right != null && right.getPos().equals(p)) {
            tmp = right;
            right = null;
        }
        return tmp;
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

    }

    public DfsNode exploreForPos(DfsI dfsi, IntPoint p) {
        // check map if we can go here
        if (dfsi.isPositionViable(p)) {

            // check if we already have been here
            Optional<DfsNode> optionalDfsNode = dfsi.findByPosition(p);

            if (optionalDfsNode.isPresent()) {
                System.out.println("Already explored: " + p);
                DfsNode parent = optionalDfsNode.get();
                // check if it is closer this way
                if (steps < parent.steps) {
                    System.out.println("Stealing dfs at: " + p);
                    DfsNode child = parent.takeChildAtPos(p);
                    child.setSteps(steps + 1);
                    return child;
                } else {
                    // old way was closer, skip this
                    return null;
                }
            } else {
                // we have not been here
                System.out.println("Adding: " + p);
                return new DfsNode(p, steps + 1);
            }
        }
        return null;
    }

    public Optional<DfsNode> findParentByChildPosition(IntPoint p) {
        if (up != null && up.getPos().equals(p)) {
            return Optional.of(this);
        }
        if (down != null && down.getPos().equals(p)) {
            return Optional.of(this);
        }
        if (left != null && left.getPos().equals(p)) {
            return Optional.of(this);
        }
        if (right != null && right.getPos().equals(p)) {
            return Optional.of(this);
        }

        Optional<DfsNode> child = Optional.empty();
        if (up != null) {
            child = up.findParentByChildPosition(p);
        }
        if (down != null && child != null) {
            child = down.findParentByChildPosition(p);
        }
        if (left != null && child != null) {
            child = left.findParentByChildPosition(p);
        }
        if (right != null && child != null) {
            child = right.findParentByChildPosition(p);
        }
        return child;
    }

    /**
     * Override for the startposition
     *
     * @param node
     */
    public void setAllChildrenAsOne(DfsNode node) {
        up = node;
        down = node;
        left = node;
        right = node;
    }
}
