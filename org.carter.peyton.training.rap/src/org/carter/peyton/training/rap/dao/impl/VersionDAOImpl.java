package org.carter.peyton.training.rap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.carter.peyton.training.rap.dao.VersionDAO;
import org.carter.peyton.training.rap.models.Version;

public class VersionDAOImpl implements VersionDAO{

	private EntityManagerFactory emf;
	
	@Override
	public Version getVersionByName(String versionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> getListVersions() {
		emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select v from Version v");
		@SuppressWarnings("unchecked")
		List<Version> listVersions = query.getResultList();
		em.close();
		
		return listVersions;
	}

}
