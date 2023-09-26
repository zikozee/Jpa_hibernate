package com.ziko;

import com.ziko.entities.onetoone.User;
import com.ziko.persistence.CustomPersistenceUnitInfo2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class SecondaryTableMain {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), props);
        EntityManager em = emf.createEntityManager();

       try{
           em.getTransaction().begin();

           User user = new User();
           user.setName("John");
           user.setDescription("Some desc");

           em.persist(user);

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}