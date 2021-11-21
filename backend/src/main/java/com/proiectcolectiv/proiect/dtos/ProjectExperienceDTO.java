package com.proiectcolectiv.proiect.dtos;

import com.proiectcolectiv.proiect.entities.ProjectsEntity;
import com.proiectcolectiv.proiect.entities.UserEntity;

import javax.persistence.*;
import java.sql.Date;

public class ProjectExperienceDTO {
    private Long id;
    private Long user;
    private Long projectsOfUser;
    private Date startDate;
    private Date endDate;
    private String role;
    private String technologies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getProjectsOfUser() {
        return projectsOfUser;
    }

    public void setProjectsOfUser(Long projectsOfUser) {
        this.projectsOfUser = projectsOfUser;
    }

    public ProjectExperienceDTO(Long id, Long user, Long projectsOfUser, Date startDate, Date endDate, String role, String technologies) {
        this.id = id;
        this.user = user;
        this.projectsOfUser = projectsOfUser;
        this.startDate = startDate;
        this.endDate = endDate;
        this.role = role;
        this.technologies = technologies;
    }
}
