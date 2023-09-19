package com.ziko;

import com.ziko.entities.Product;
import com.ziko.persistence.CustomPersistenceUnitInfo2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); // for xml
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), new HashMap<>());
        EntityManager em = emf.createEntityManager(); // represents the context

       try{
           em.getTransaction().begin();

           Product product = new Product();
           product.setId(3);
           product.setName("Books");

           em.persist(product);  // add this to context  --> NOT AN INSERT QUERY

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}