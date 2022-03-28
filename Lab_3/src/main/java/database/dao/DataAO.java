package database.dao;

import database.hibernate.HibernateSessionFactory;
import entity.Shots;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Data
public class DataAO {

    private EntityManagerFactory managerFactory;

    public DataAO(){
        managerFactory = HibernateSessionFactory.getManagerFactory();
    }

    public List<Shots> getShots(){
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Shots> result = null;
        try {
            transaction.begin();
            result = entityManager.createQuery("FROM Shots").getResultList();
            transaction.commit();
        } catch (Exception exception){
            try {
                System.err.println("Error in get shots");
                exception.printStackTrace();
                transaction.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        entityManager.close();
        return result;
    }

    public void clearTable(){
        System.out.println("Clearing start");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.createQuery("DELETE FROM Shots").executeUpdate();
            transaction.commit();
        } catch (Exception exception){
            try {
                System.err.println("Error in clear table.");
                exception.printStackTrace();
                transaction.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        entityManager.close();
    }

    public void addShot(Shots shot){
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(shot);
            transaction.commit();
        } catch(Exception exception){
            try {
                System.err.println("Error in add shot.");
                exception.printStackTrace();
                transaction.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
