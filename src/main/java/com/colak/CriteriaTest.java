package com.colak;

import com.colak.jpa.Employee2;
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
        Employee2 employee2 = new Employee2();
        employee2.setFirstName("John");
        employee2.setLastName("Doe");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(employee2);
        log.info("Employee id : {}", employee2.getId());

        entityManager.flush();
        entityManager.getTransaction().commit();


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee2> criteriaQuery = criteriaBuilder.createQuery(Employee2.class);
        Root<Employee2> root = criteriaQuery.from(Employee2.class);

        Predicate equalPredicate = criteriaBuilder.equal(root.get("firstName"), "John");
        criteriaQuery.select(root).where(equalPredicate);

        TypedQuery<Employee2> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Employee2> employees = typedQuery.getResultList();
        log.info("Employees : {}", employees);

        entityManager.close();
        entityManagerFactory.close();

    }
}
