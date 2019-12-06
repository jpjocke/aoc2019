package com.advent;

import java.util.ArrayList;
import java.util.List;

public class SpaceObject {
    String id;
    SpaceObject parent;
    List<SpaceObject> children;

    public SpaceObject(SpaceObject parent, String id) {
        this.id = id;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public boolean add(SpaceObject spaceObject) {
        return children.add(spaceObject);
    }

    public List<SpaceObject> getChildren() {
        return children;
    }

    public SpaceObject getParent() {
        return parent;
    }
}
