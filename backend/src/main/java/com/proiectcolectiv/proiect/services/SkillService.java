package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.entities.Skill;
import com.proiectcolectiv.proiect.entities.SkillOfUser;
import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.repositories.SkillOfUserRepository;
import com.proiectcolectiv.proiect.repositories.SkillRepository;
import com.proiectcolectiv.proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final SkillOfUserRepository skillOfUserRepository;


    @Autowired
    public SkillService(SkillRepository skillRepository, UserRepository userRepository, SkillOfUserRepository skillOfUserRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
        this.skillOfUserRepository = skillOfUserRepository;
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill save(Skill skill) {
        Optional<Skill> optionalSkill;

        optionalSkill = skillRepository.findSkillBySkill(skill.getSkill());

        Skill p1 = optionalSkill.isPresent() ? null : skillRepository.save(skill);

        return ofNullable(p1)
                .orElseThrow(() -> new EntityExistsException("Skill already exist: " + skill.getSkill()));
    }

    public Optional<Skill> findSkillBySkill(String skill) {
        return skillRepository.findSkillBySkill(skill);
    }

    public List<UserEntity> searchBySkill(List<Skill> skillList) {
        List<UserEntity> users = userRepository.findAll();
        List<UserEntity> newUsers = new ArrayList<>();
        List<Boolean> checkSkills = new ArrayList<>();
        for (int i = 0; i < users.size(); i++)
            checkSkills.add(true);
        for (Skill skill : skillList) {
            for (UserEntity userEntity : users) {
                List<SkillOfUser> skills = userEntity.getUserSkills();
                boolean hasSkill = false;
                for (SkillOfUser skillOfUser : skills)
                    if (skillOfUser.getSkills().equals(skill.getSkill())) {
                       hasSkill = true;
                    }
                if(!hasSkill){
                    int index = users.indexOf(userEntity);
                    checkSkills.remove(index);
                    checkSkills.add(index, false);
                }
            }
        }
        for(int i =0; i<users.size();i++)
            if (checkSkills.get(i)) {
                newUsers.add(users.get(i));
                System.out.println(users.get(i).getId());
            }
        return newUsers;
    }

}
