package com.colak.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Instead of JPA event annotations we can also use Hibernate Event Listeners
 * See <a href="https://medium.com/jpa-java-persistence-api-guide/hibernate-v6-event-interception-250ae115f0db">...</a>
 * <p>
 * org.hibernate.event.spi.PreInsertEventListener -- equal to Jakarta @PrePersist
 * org.hibernate.event.spi.PostInsertEventListener -- equal to Jakarta @PostPersist
 * <p>
 * org.hibernate.event.spi.PreUpdateEventListener -- equal to Jakarta @PreUpdate
 * org.hibernate.event.spi.PostUpdateEventListener -- equal to Jakarta @PostUpdate
 * <p>
 * org.hibernate.event.spi.PreDeleteEventListener -- equal to Jakarta @PreRemove
 * org.hibernate.event.spi.PostDeleteEventListener -- equal to Jakarta @PostRemove
 * <p>
 * org.hibernate.event.spi.PreLoadEventListener -- no annotation ?
 * org.hibernate.event.spi.PostLoadEventListener -- equal to Jakarta @PostLoad
 */
@Entity
// "user" is reserved for H2. So give the table another name
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @PrePersist
    public void prePersist() {
        log.info("prePersist is called");
    }

    @PostPersist
    public void postPersist() {
        log.info("postPersist is called");
    }

    @PostLoad
    public void postLoad() {
        log.info("postLoad is called");
    }

}