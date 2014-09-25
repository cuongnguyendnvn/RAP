package org.carter.peyton.training.rap.domain;

public class TreeObject extends Object {

    private TreeParent treeParent;

    public TreeParent getParent() {
        return treeParent;
    }

    public void setParent(TreeParent treeParent) {
        this.treeParent = treeParent;
    }
}
