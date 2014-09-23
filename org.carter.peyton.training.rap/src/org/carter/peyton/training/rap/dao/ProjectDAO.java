package org.carter.peyton.training.rap.dao;

import java.util.List;

import org.carter.peyton.training.rap.models.Project;

public interface ProjectDAO {
	public Project getProjectById (int projectId);
	public List<Project> getListProjects();
}
