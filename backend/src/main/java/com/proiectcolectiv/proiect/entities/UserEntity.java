package com.proiectcolectiv.proiect.entities;

import javax.persistence.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "id_supervisor", nullable = true)
    private Long idSupervisor;

    public String getProfilePicType() {
        return profilePicType;
    }

    public void setProfilePicType(String profilePicType) {
        this.profilePicType = profilePicType;
    }

    public String getProfilePicName() {
        return profilePicName;
    }

    public void setProfilePicName(String profilePicName) {
        this.profilePicName = profilePicName;
    }

    public UserEntity(String profilePicName, String profilePicType, byte[] profilePic) {
        this.profilePicName = profilePicName;
        this.profilePicType = profilePicType;
        this.profilePic = profilePic;
    }

    public UserEntity(Long id, String password, String email, byte[] profilePic, Role role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.profilePic = profilePic;
        this.role = role;
    }

    public UserEntity() {

    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    @Column(name = "profilepic", nullable = true)
    private byte[] profilePic;

    @Column(name = "profilepictype", nullable = true)
    private String profilePicType;

    @Column(name = "profilepicname", nullable = true)
    private String profilePicName;
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public List<ProjectsEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectsEntity> projects) {
        this.projects = projects;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(name = "user_project",
            joinColumns =  @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id") )
    private List<ProjectsEntity> projects;

   @OneToMany(mappedBy = "user")
   private List<SkillOfUser> userSkills;

    public List<SkillOfUser> getUserSkills() {
        return userSkills;
    }
    public List<String> getProjectNames() {
        List<String> projectNames= new ArrayList<>();
        for(ProjectsEntity projectsEntity: projects){
            projectNames.add(projectsEntity.getName());
        }
        return projectNames;
    }


    public List<String> getSkills() {
        List<String> skills= new ArrayList<>();
        for(SkillOfUser skillOfUser: userSkills){
            skills.add(skillOfUser.getSkills());
        }
        return skills;
    }


    public void setUserSkills(List<SkillOfUser> userSkills) {
        this.userSkills = userSkills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getId() {
        return id;
    }


    public String getPasswordHash() {
        return password;
    }

    public void setPasswordHash(String passwordHash) {
        this.password = passwordHash;
    }

    public Long getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Long idSupervisor) {
        this.idSupervisor = idSupervisor;
    }
}
