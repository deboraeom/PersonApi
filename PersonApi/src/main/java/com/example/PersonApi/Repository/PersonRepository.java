package com.example.PersonApi.Repository;

import com.example.PersonApi.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
