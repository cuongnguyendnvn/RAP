package org.carter.peyton.training.rap.db.dao;

import java.util.List;

import org.carter.peyton.training.rap.db.entities.Project;

public interface ProjectDAO {
    public Project getProjectById (int projectId);
    public List<Project> getListProjects();
}
