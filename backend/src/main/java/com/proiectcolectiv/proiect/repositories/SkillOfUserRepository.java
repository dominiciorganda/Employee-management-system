package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.Skill;
import com.proiectcolectiv.proiect.entities.SkillOfUser;
import com.proiectcolectiv.proiect.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillOfUserRepository  extends JpaRepository<SkillOfUser, Long> {
    Optional<SkillOfUser> findSkillOfUserByUserAndSkills(UserEntity user, Skill skill);
}
