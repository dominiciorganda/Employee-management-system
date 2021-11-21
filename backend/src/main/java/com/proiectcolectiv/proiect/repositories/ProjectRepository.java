package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectsEntity, Long> {
    public List<ProjectsEntity> findAll();
    public Optional<ProjectsEntity> findProjectsEntityByName(String name);
}
