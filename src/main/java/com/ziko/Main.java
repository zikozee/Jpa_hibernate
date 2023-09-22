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
        props.put("hibernate.hbm2ddl.auto", "create"); //create, update, validate, none

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); // for xml
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), props);
        EntityManager em = emf.createEntityManager(); // represents the context

       try{
           em.getTransaction().begin();


           // no change will occur since the below mirrors what is in the database already
           Employee e1 = new Employee();
           e1.setName("Ziko");
           e1.setAddress("123 Bus st");

           em.persist(e1);

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}