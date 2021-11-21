package com.proiectcolectiv.proiect.dtos;

import com.proiectcolectiv.proiect.entities.Role;
import com.proiectcolectiv.proiect.entities.UserEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
    private String email;
    private String password;
    private Role role;
    private List<SkillDTO> skills;

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
