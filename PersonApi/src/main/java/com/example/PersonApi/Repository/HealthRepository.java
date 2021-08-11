package com.example.PersonApi.Repository;

import com.example.PersonApi.Entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Long> {
}
