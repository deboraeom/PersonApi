package com.example.PersonApi.Repository;

import com.example.PersonApi.Entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
