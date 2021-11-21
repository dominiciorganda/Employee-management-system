package com.proiectcolectiv.proiect.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "skill", nullable = false)
    private String skill;
    @OneToMany(mappedBy = "skills")
    private List<SkillOfUser> skills;

    public Skill(Long id, String skill) {
        this.id = id;
        this.skill = skill;
    }

    public Skill() {
    }

    public Long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill that = (Skill) o;

        if (!id.equals(that.id)) return false;
        if (skill != null ? !skill.equals(that.skill) : that.skill != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return getSkill();
    }
}
