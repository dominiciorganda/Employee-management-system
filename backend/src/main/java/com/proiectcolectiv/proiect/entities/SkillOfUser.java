package com.proiectcolectiv.proiect.entities;

import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Table(name = "user_skill")
public class SkillOfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skills;

    @Column(name = "grade")
    private int grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getSkills() {
        return skills.getSkill();
    }

    public void setSkill(Skill skill) {
        this.skills = skill;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
