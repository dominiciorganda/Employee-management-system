package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.entities.ProjectsEntity;
import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.repositories.ProjectRepository;
import com.proiectcolectiv.proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public List<ProjectsEntity> findAll() {
        return projectRepository.findAll();
    }

    public ProjectsEntity save(ProjectsEntity project) {
        Optional<ProjectsEntity> optionalProject;

        optionalProject = projectRepository.findProjectsEntityByName(project.getName());

        ProjectsEntity p1 = optionalProject.isPresent() ? null : projectRepository.save(project);

        return ofNullable(p1)
                .orElseThrow(() -> new EntityExistsException("Project already exist: " + project.getName()));
    }

}
