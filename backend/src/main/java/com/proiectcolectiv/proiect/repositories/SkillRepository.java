package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAll();
    Optional<Skill> findSkillBySkill(String skill);
}
