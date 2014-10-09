package org.carter.peyton.training.rap.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.carter.peyton.training.rap.db.dao.UserDAO;
import org.carter.peyton.training.rap.db.entities.User;

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory emf;

    @Override
    public List<User> getListUser() {
        emf = Persistence.createEntityManagerFactory("org.carter.peyton.training.rap.db");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select u from User u");
        @SuppressWarnings("unchecked")
        List<User> listUser = query.getResultList();
        em.close();

        return listUser;
    }
}
