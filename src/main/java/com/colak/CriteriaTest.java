package com.colak;

import com.colak.jpa.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * See <a href="https://towardsdev.com/mastering-jpa-in-spring-boot-a-comprehensive-guide-dd296eb6df6f">...</a>
 */
@Slf4j
public class CriteriaTest {

    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        log.info("User id : {}", user.getId());

        entityManager.flush();
        entityManager.getTransaction().commit();


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate equalPredicate = criteriaBuilder.equal(root.get("firstName"), "John");
        criteriaQuery.select(root).where(equalPredicate);

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        List<User> userList = typedQuery.getResultList();
        log.info("UserList : {}", userList);

        entityManager.close();
        entityManagerFactory.close();

    }
}
