package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.dtos.ProjectExperienceDTO;
import com.proiectcolectiv.proiect.entities.*;
import com.proiectcolectiv.proiect.repositories.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final UserRepository userRepository;
    private final SkillOfUserRepository skillOfUserRepository;
    private final SkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final ProjectOfUserRepository projectOfUserRepository;

    @Autowired
    public EmployeeService(UserRepository userRepository, SkillOfUserRepository skillOfUserRepository, SkillRepository skillRepository, ProjectRepository projectRepository, ProjectOfUserRepository projectOfUserRepository) {
        this.userRepository = userRepository;
        this.skillOfUserRepository = skillOfUserRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.projectOfUserRepository = projectOfUserRepository;
    }

    public ArrayList<ProjectOfUser> getUserProjects(Long userId){
        Optional<UserEntity> optionalUser;
        optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new ServiceException("No such user");
        }
        return projectOfUserRepository.findProjectOfUserByUser(optionalUser.get());
    }

    public SkillOfUser saveProject(ProjectExperienceDTO projectExperienceDTO) {
        Optional<UserEntity> optionalUser;
        optionalUser = userRepository.findById(projectExperienceDTO.getUser());

        Optional<ProjectsEntity> optionalProject;
        optionalProject = projectRepository.findById(projectExperienceDTO.getProjectsOfUser());

        if(optionalUser.isEmpty()){
            throw new ServiceException("No such user");
        }
        if(optionalProject.isEmpty()){
            throw new ServiceException("No such project");
        }

        ProjectOfUser projectOfUser = new ProjectOfUser();
        projectOfUser.setUser(optionalUser.get());
        projectOfUser.setProject(optionalProject.get());
        projectOfUser.setStartDate(projectExperienceDTO.getStartDate());
        projectOfUser.setEndDate(projectExperienceDTO.getEndDate());
        projectOfUser.setRole(projectExperienceDTO.getRole());
        projectOfUser.setTechnologies(projectExperienceDTO.getTechnologies());

        projectOfUserRepository.save(projectOfUser);
        return null;
    }

    public SkillOfUser saveSkill(Long userId, Long skillId, int grade) {
        Optional<UserEntity> optionalUser;
        optionalUser = userRepository.findById(userId);

        Optional<Skill> optionalSkill;
        optionalSkill = skillRepository.findById(skillId);

        if(optionalUser.isEmpty()){
            throw new ServiceException("No such user");
        }
        if(optionalSkill.isEmpty()){
            throw new ServiceException("No such skill");
        }

        Optional<SkillOfUser> hasUserSkill;
        hasUserSkill = skillOfUserRepository.findSkillOfUserByUserAndSkills(optionalUser.get(),optionalSkill.get());

        if(!hasUserSkill.isEmpty()){
            throw new ServiceException("Skill already exists");
        }else{

            SkillOfUser s = new SkillOfUser();
            s.setUser(optionalUser.get());
            s.setSkill(optionalSkill.get());
            s.setGrade(grade);

            skillOfUserRepository.save(s);
        }

        return null;
    }
}
