package org.carter.peyton.training.rap.models;

import java.util.List;

public class TreeObject {
	
	private User user;
	private int index;
	private List<Project> projects;
	private TreeParent treeParent;
	
	public List<Project> getProject() {
		return projects;
	}

	public void setProject(List<Project> projects) {
		this.projects = projects;
	}

	public TreeParent getParent() {
		return treeParent;
	}

	public void setParent(TreeParent treeParent) {
		this.treeParent = treeParent;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		if (getIndex() == 3) {
			return projects.toString();
		} else {
			return user.toString();
		}
	}
}
