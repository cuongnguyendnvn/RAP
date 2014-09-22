package org.carter.peyton.training.rap.models;

import java.util.ArrayList;
import java.util.List;

public class ProjectParent extends Project {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4816291866079788335L;
	
	private final List<Project> projects;
	
	public ProjectParent() {
		super();
		projects = new ArrayList<Project>();
	}
	
	public void addProjectChild(Project child) {
		projects.add(child);
	}

	public Project[] getProjectChildren() {
		Project[] result = new Project[projects.size()];
		projects.toArray(result);
		return result;
	}

	public boolean hasProjectChildren() {
		return projects.size() > 0;
	}
}
