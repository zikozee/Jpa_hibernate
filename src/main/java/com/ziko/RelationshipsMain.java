package com.ziko;

import com.ziko.entities.Student;
import com.ziko.entities.onetoone.Passport;
import com.ziko.entities.onetoone.Person;
import com.ziko.keys.StudentKey;
import com.ziko.persistence.CustomPersistenceUnitInfo2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class RelationshipsMain {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), props);
        EntityManager em = emf.createEntityManager();

       try{
           em.getTransaction().begin();

           Passport passport = new Passport();
           passport.setNumber("BB450687IA");

           Person person = new Person();
           person.setName("John");

           person.setPassport(passport);
           passport.setPerson(person);

           // no specific other since its just loading to context and not saving to database
           em.persist(person);
           em.persist(passport);

           TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.passport.number= :number", Person.class);
           q.setParameter("number", "BB450687IA");
           System.out.println(q.getResultList());

           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}