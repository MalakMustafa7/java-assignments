package org.example.repository;

import org.example.config.HibernateUtil;
import org.example.model.Category;
import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductRepository {
    public void save(Product product){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

    public Product findById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Product.class, id);
        }
    }
    public List< Product> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return  session.createQuery("from Product", Product.class)
                    .getResultList();
        }
    }

    public void update(Product product){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
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
            Product managedProduct= session.find(Product.class, id);
            session.remove(managedProduct);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

}
