package com.colak;

import com.colak.jpa.Employee2;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

        HazelcastInstance hazelcastInstance = getHazelcastInstance();
        String name = hazelcastInstance.getName();
        log.info("Hazelcast Client Name : {}", name);
        IMap<Long, Employee2> map = hazelcastInstance.getMap("employee");
        log.info("employee Map Size : {}", map.size());

        Employee2 employee2 = new Employee2();
        employee2.setFirstName("John");
        employee2.setLastName("Doe");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(employee2);
        log.info("Employee id : {}", employee2.getId());

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        hazelcastInstance.shutdown();
    }

    public static HazelcastInstance getHazelcastInstance() {
        ClientConfig config = new ClientConfig();
        config.setInstanceName("hz1");
        // Customize Hazelcast configuration as needed
        return HazelcastClient.newHazelcastClient(config);
    }
}