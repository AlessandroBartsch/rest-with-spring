package com.restwithspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restwithspring.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
