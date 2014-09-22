package org.carter.peyton.training.rap.models;

import java.util.ArrayList;
import java.util.List;

public class TreeParent extends TreeObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8567546609995222836L;
	private final List<TreeObject> children;

	public TreeParent() {
		super();
		children = new ArrayList<TreeObject>();
	}

	public void addChild(TreeObject child) {
		children.add(child); 
	}

	public TreeObject[] getChildren() {
		TreeObject[] result = new TreeObject[children.size()];
		children.toArray(result);
		return result;
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}
	
	@Override
	public String toString() {
		return children.get(0).getUser().getUserName();
	}
}
