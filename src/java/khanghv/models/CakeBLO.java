/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import khanghv.dtos.Category;
import khanghv.dtos.MoonCake;

/**
 *
 * @author USER
 */
public class CakeBLO implements Serializable {

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

    public List<MoonCake> getListCake() {
        List<MoonCake> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        String sql = "Select cake From MoonCake cake";
        Query query = em.createQuery(sql);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            return list;
        }
        Collections.sort(list);
        return list;
    }

    public List<MoonCake> getListCakeByName(String search) {
        List<MoonCake> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        String sql = "Select cake From MoonCake cake Where cake.cakeName Like :cakeName";
        Query query = em.createQuery(sql);
        query.setParameter("cakeName", "%" + search + "%");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            return list;
        }
        Collections.sort(list);
        return list;
    }

    public List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        String sql = "Select category From Category category";
        Query query = em.createQuery(sql);
        try {

            list = query.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            return list;
        }
        
        return list;
    }

    public List<MoonCake> getListCakeByCategory(Category category) {
        List<MoonCake> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        String sql = "Select cake From MoonCake cake Where cake.categoryID = :Category";
        Query query = em.createQuery(sql);
        query.setParameter("Category", category);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            return list;
        }
        Collections.sort(list);
        return list;
    }

    public List<MoonCake> getListPrice(int num1, int num2) {
        List<MoonCake> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        String sql = "Select mooncake From MoonCake mooncake Where mooncake.price >= :price1 And mooncake.price <= :price2";
        Query query = em.createQuery(sql);
        query.setParameter("price1", num1);
        query.setParameter("price2", num2);

        try {

            list = query.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            return list;
        }
        Collections.sort(list);
        return list;
    }

    public MoonCake getDetail(String id) {
        MoonCake cake = null;
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("MoonCake.findByIDCake");
        query.setParameter("iDCake", id);
        try {
            cake = (MoonCake) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return cake;
    }

    public boolean updateCake(MoonCake c) throws Exception {
        EntityManager em = emf.createEntityManager();
        MoonCake mc = em.find(MoonCake.class, c.getIDCake());
        if(mc != null)
        {
            mc.setCakeName(c.getCakeName());
            mc.setPrice(c.getPrice());
            mc.setQuantity(c.getQuantity());
            mc.setImage(c.getImage());
            mc.setCreateDate(c.getCreateDate());
            mc.setDescription(c.getDescription());
            mc.setExpireDate(c.getExpireDate());
            mc.setLastUpdate(c.getLastUpdate());
            mc.setStatus(c.getStatus());
            mc.setUserUpdate(c.getUserUpdate());
            mc.setCategoryID(c.getCategoryID());
            em.getTransaction().begin();
            em.merge(mc);
            em.getTransaction().commit();
            return true;
        }
        return false;
        
    }
    
    public boolean updateCakeAfterCheck(MoonCake c) throws Exception {
        EntityManager em = emf.createEntityManager();
        MoonCake mc = em.find(MoonCake.class, c.getIDCake());
        if(mc != null)
        {
            mc.setQuantity(c.getQuantity());
            em.getTransaction().begin();
            em.merge(mc);
            em.getTransaction().commit();
            return true;
        }
        return false;
        
    }
    
    
    public int getLastId() throws Exception
    {
        EntityManager em = emf.createEntityManager();
        List<String> list = new ArrayList<>();
        String sql = "Select IDCake From MoonCake";
        Query q = em.createNativeQuery(sql);
        try {
            list = q.getResultList();
        } catch (Exception e) {
            return 0;
        }
        return list.size();
    }
    
    public boolean addCake(MoonCake c) throws Exception
    {
        EntityManager em = emf.createEntityManager();
        MoonCake mc = em.find(MoonCake.class, c.getIDCake());
        if(mc == null)
        {   
            mc = new MoonCake(c.getIDCake());
            mc.setCakeName(c.getCakeName());
            mc.setPrice(c.getPrice());
            mc.setQuantity(c.getQuantity());
            mc.setImage(c.getImage());
            mc.setCreateDate(c.getCreateDate());
            mc.setDescription(c.getDescription());
            mc.setExpireDate(c.getExpireDate());
            mc.setLastUpdate(c.getLastUpdate());
            mc.setStatus(c.getStatus());
            mc.setUserUpdate(c.getUserUpdate());
            mc.setCategoryID(c.getCategoryID());
            em.getTransaction().begin();
            em.merge(mc);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

}
