package com.ziko;

import com.ziko.entities.Employee;
import com.ziko.entities.Product;
import com.ziko.persistence.CustomPersistenceUnitInfo2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update"); //create, update, validate, none

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); // for xml
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), props);
        EntityManager em = emf.createEntityManager(); // represents the context

       try{
           em.getTransaction().begin();

           var e1 = em.getReference(Employee.class, 1);
           System.out.println(e1);

           e1.setName("Gee");

           em.refresh(e1); //uses what is in the database in place of the update issues by setting some data

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}