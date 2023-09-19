package com.ziko;

import com.ziko.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager(); // represents the context

       try{
           em.getTransaction().begin();

           Product product = new Product();
           product.setId(1);
           product.setName("Beer");

           em.persist(product);  // add this to context  --> NOT AN INSERT QUERY

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}