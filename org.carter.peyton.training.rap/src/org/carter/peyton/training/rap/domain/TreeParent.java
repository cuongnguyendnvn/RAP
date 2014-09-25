package org.carter.peyton.training.rap.domain;

import java.util.ArrayList;
import java.util.List;

public class TreeParent extends Object {

    private final List<Object> children;
    private String rootName;
    private boolean isUserObject = false;
    private boolean isProjectObject = false;

    public TreeParent() {
        super();
        children = new ArrayList<Object>();
    }

    public void addChild(Object child) {
        children.add(child); 
    }

    public Object[] getChildren() {
        Object[] result = new Object[children.size()];
        children.toArray(result);
        return result;
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }
    
    public boolean isUserObject() {
        return isUserObject;
    }

    public void setUserObject(boolean isUserObject) {
        this.isUserObject = isUserObject;
    }

    public boolean isProjectObject() {
        return isProjectObject;
    }

    public void setProjectObject(boolean isProjectObject) {
        this.isProjectObject = isProjectObject;
    }

    @Override
    public String toString() {
        return getRootName();
    }
}
