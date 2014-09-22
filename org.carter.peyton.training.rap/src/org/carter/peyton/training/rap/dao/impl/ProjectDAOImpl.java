package org.carter.peyton.training.rap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.carter.peyton.training.rap.dao.ProjectDAO;
import org.carter.peyton.training.rap.models.Project;

public class ProjectDAOImpl implements ProjectDAO {

	private EntityManagerFactory emf;
	
	@Override
	public Project getProjectByName(String projectName) {
		return null;
	}

	@Override
	public List<Project> getListProjects() {
		emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select p from Project p");
		@SuppressWarnings("unchecked")
		List<Project> listProject = query.getResultList();
		em.close();
		
		return listProject;
	}
	
}
