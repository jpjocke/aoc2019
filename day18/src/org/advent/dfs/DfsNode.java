package org.advent.dfs;

import org.advent.util.IntPoint;

import java.util.Optional;


public class DfsNode {
    private IntPoint pos;
    private int steps;
    private DfsNode parent;
    private DfsNode up;
    private DfsNode down;
    private DfsNode left;
    private DfsNode right;
  //  private boolean done;
  //  private char key;

    public DfsNode(DfsNode parent, IntPoint pos, int steps) {
        System.out.println("Created: " + pos + " steps: " + steps);
        this.pos = pos;
        this.steps = steps;
        this.parent = parent;
   //     done = false;
    }

    public IntPoint getPos() {
        return pos;
    }

    public void explore(DfsI dfsi) {
        System.out.println("Exploring: " + pos + " steps: " + steps);
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

    public void removeFromParent() {
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
    }

    public DfsNode exploreForPos(DfsI dfsi, IntPoint p) {
        // check map if we can go here
        if (dfsi.isPositionViable(p)) {

            // check if we already have been here
            Optional<DfsNode> optionalDfsNode = dfsi.findByPosition(p);

            if (optionalDfsNode.isPresent()) {
                DfsNode node = optionalDfsNode.get();
                System.out.println("Already explored: " + p + " had steps: " + node.getSteps());
                // check if it is closer this way
                if (node.getSteps() > steps + 1) {
                    System.out.println("Stealing dfs at: " + p);
                    node.removeFromParent();
                    node.setSteps(steps + 1);
                    return node;
                } else {
                    // old way was closer, skip this
                    return null;
                }
            } else {
                // we have not been here
                System.out.println("Adding: " + p);
                return new DfsNode(this, p, steps + 1);
            }
        }
        return null;
    }

    public Optional<DfsNode> findByPosition(IntPoint p) {
        if (pos.equals(p)) {
            return Optional.of(this);
        }
        Optional<DfsNode> child = Optional.empty();
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

        return child;
    }

    public int getSteps() {
        return steps;
    }
}
