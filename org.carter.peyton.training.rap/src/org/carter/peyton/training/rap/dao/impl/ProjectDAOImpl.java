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
	public Project getProjectById(int projectId) {
		emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select p from Project p where p.idProject = :projectId");
		query.setParameter("projectId", projectId);
		Project project = (Project)query.getSingleResult();
		em.close();
		
		return project;
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
