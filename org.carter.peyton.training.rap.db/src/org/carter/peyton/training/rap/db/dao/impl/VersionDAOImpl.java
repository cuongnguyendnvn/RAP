package org.carter.peyton.training.rap.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.carter.peyton.training.rap.db.dao.VersionDAO;
import org.carter.peyton.training.rap.db.entities.Version;

public class VersionDAOImpl implements VersionDAO{

    private EntityManagerFactory emf;

    @Override
    public Version getVersionByName(String versionName) {
        return null;
    }

    @Override
    public List<Version> getListVersions() {
        emf = Persistence.createEntityManagerFactory("org.carter.peyton.training.rap.db");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select v from Version v");
        @SuppressWarnings("unchecked")
        List<Version> listVersions = query.getResultList();
        em.close();

        return listVersions;
    }

}
