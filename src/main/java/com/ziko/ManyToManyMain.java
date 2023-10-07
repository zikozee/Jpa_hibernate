
package com.ziko;

import com.ziko.entities.manytomany.Customer;
import com.ziko.entities.manytomany.Group;
import com.ziko.entities.onetomany.Comment;
import com.ziko.entities.onetomany.Post;
import com.ziko.persistence.CustomPersistenceUnitInfo2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManyToManyMain {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
//        props.put("hibernate.hbm2ddl.auto", "update");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), props);
        EntityManager em = emf.createEntityManager();

       try{
           em.getTransaction().begin();

           Customer customer1 = new Customer();
           customer1.setName("Customer 1");

           Customer customer2 = new Customer();
           customer2.setName("Customer 2");

           Group group1 = new Group();
           group1.setName("Group 1");

           Group group2 = new Group();
           group2.setName("Group 2");

           group1.setCustomers(List.of(customer1, customer2));
           group2.setCustomers(List.of(customer2));

// since no CASCADE IS USED, i have to manually persist
           em.persist(customer1);
           em.persist(customer2);
           em.persist(group1);
           em.persist(group2);


           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}