package org.carter.peyton.training.rap.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.carter.peyton.training.rap.db.dao.DeviceDAO;
import org.carter.peyton.training.rap.db.entities.Device;


public class DeviceDAOImpl implements DeviceDAO {

    private EntityManagerFactory emf;

    @SuppressWarnings("unchecked")
    @Override
    public List<Device> getDeviceList() {
        emf = Persistence.createEntityManagerFactory("org.carter.peyton.training.rap.db");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select d from Device d");
        List<Device> listDevices = query.getResultList();
        em.close();

        return listDevices;
    }

}
