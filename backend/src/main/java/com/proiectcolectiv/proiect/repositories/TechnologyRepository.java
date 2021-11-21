package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findAll();
    Optional<Technology> findTechnologyByTechnology(String technology);
}
