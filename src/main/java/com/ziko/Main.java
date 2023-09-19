package com.ziko;

import com.ziko.entities.Employee;
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

//           Product product = new Product();
//           product.setId(3);
//           product.setName("Books");
//
//           em.persist(product);  // add this to context  --> NOT AN INSERT QUERY

           //finding entity
           Employee employee = em.find(Employee.class, 1); // if this already occur in the context, it doesn't pick from the database
           System.out.println("employee: " +employee);

           // no change will occur since the below mirrors what is in the database already
           Employee e2 = new Employee();
           e2.setId(1);
           e2.setName("Mary");
           e2.setAddress("123 Bus st");

           em.merge(e2);

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}