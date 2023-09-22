package com.ziko;

import com.ziko.entities.Employee;
import com.ziko.entities.Product;
import com.ziko.entities.Student;
import com.ziko.keys.StudentKey;
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

           StudentKey id = new StudentKey();
           id.setNumber(34L);
           id.setCode("Code1");

//           Student student = new Student();
//           student.setId(id);
//           student.setName("Bilbo Baggins");
//
//           em.persist(student);

           Student s = em.find(Student.class, id);
           System.out.println(s);

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}