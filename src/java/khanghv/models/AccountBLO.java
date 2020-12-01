/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.models;

import khanghv.dtos.Account;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class AccountBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("J3LP0011PU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Account checkLogin(String username, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account acc = null;
        String sql = "Select a from Account a Where a.userID = :userID And a.password = :password";
        try {
            Query query = em.createQuery(sql);
            query.setParameter("userID", username);
            query.setParameter("password", password);
            acc = (Account) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return acc;
    }

}
