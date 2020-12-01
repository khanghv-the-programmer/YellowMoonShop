/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.models;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import khanghv.dtos.OrderDetail;
import khanghv.dtos.ProductOrder;

/**
 *
 * @author USER
 */
public class CartBLO {

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

    public Integer addCart(ProductOrder cart) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cart);
            em.getTransaction().commit();

        } catch (Exception e) {
            return 0;
        }
        return cart.getOrderId();
    }
    
     public ProductOrder getDetail(int id) {
        ProductOrder cart = null;
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("ProductOrder.findByOrderId");
        query.setParameter("orderId", id);
        try {
            cart = (ProductOrder) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return cart;
    }
     
     public List<OrderDetail> getDetailByOrder(ProductOrder cart) {
        List<OrderDetail> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        String sql = "Select detail From OrderDetail detail Where detail.orderId = :order";
        Query query = em.createQuery(sql);
        query.setParameter("order", cart);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            return list;
        }
        return list;
    }



}
