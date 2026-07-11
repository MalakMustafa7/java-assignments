package org.example.repository;

import org.example.config.HibernateUtil;
import org.example.model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderRepository {
    public void save(Order order){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

    public Order findById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Order.class, id);
        }
    }
    public List<Order> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return  session.createQuery("from Order", Order.class)
                    .getResultList();
        }
    }

    public void update(Order order){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    public void removeById(Long id){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order managedOrder = session.find(Order.class, id);
            session.remove(managedOrder);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

}
