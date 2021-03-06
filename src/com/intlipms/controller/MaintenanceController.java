/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intlipms.controller;

import com.intlipms.entities.MaintRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Adedamola
 */
public class MaintenanceController {

    private static final String PERSISTENCE_UNIT_NAME = "IntliPMSReplicatorPU";
    private static EntityManagerFactory factory;
    private static final String PARAMETER = "replicationStatus";
    EntityManager em;
    public List<MaintRequest> getRequests() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createNamedQuery("MaintRequest.findByReplicationStatus").setParameter(PARAMETER, false);;
        
        List<MaintRequest> requestList = q.getResultList();
        
        return requestList;
    }

    public void update(int id) {
        MaintRequest m = em.find(MaintRequest.class, id);

        em.getTransaction().begin();
        m.setReplicationStatus(true);
        em.getTransaction().commit();
    }
    
    public void close(){
        em.close();
    }

}
