package org.example.repository;

import org.example.config.HibernateUtil;
import org.example.model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CategoryRepository {
    public void save(Category category){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(category);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

    public Category findById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Category.class, id);
        }
    }
    public List< Category> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return  session.createQuery("from Category", Category.class)
                    .getResultList();
        }
    }

    public void update(Category category){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(category);
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
            Category managedCategory = session.find(Category.class, id);
            session.remove(managedCategory);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }


}
