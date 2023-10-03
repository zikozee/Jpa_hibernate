package com.ziko;

import com.ziko.entities.onetomany.Comment;
import com.ziko.entities.onetomany.Post;
import com.ziko.entities.onetoone.Passport;
import com.ziko.entities.onetoone.Person;
import com.ziko.persistence.CustomPersistenceUnitInfo2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneToManyMain {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo2(), props);
        EntityManager em = emf.createEntityManager();

       try{
           em.getTransaction().begin();

           Post post = new Post();
           post.setTitle("Post 1");
           post.setContent("Post Content");

           Comment comment = new Comment();
           comment.setContent("Post Content");

           post.setComments(List.of(comment));

           em.persist(post);
           em.persist(comment);


           em.getTransaction().commit();
       }finally {
           em.close();
       }


    }
}