package com.colak.jpa;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Cacheable

@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

}