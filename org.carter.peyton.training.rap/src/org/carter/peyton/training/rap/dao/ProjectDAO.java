package org.carter.peyton.training.rap.dao;

import java.util.List;

import org.carter.peyton.training.rap.models.Project;

public interface ProjectDAO {
	public Project getProjectByName (String projectName);
	public List<Project> getListProjects();
}
