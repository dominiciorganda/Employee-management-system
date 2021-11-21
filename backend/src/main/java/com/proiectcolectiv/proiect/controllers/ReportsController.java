package com.proiectcolectiv.proiect.controllers;

import com.proiectcolectiv.proiect.dtos.SkillDTO;
import com.proiectcolectiv.proiect.dtos.UserDTO;
import com.proiectcolectiv.proiect.entities.Skill;
import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.services.SkillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialJavaObject;
import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private SkillService skillService;


    @RequestMapping(value = "/searchBySkill/", method = RequestMethod.GET)
    public ResponseEntity<?> searchBySkill(@RequestBody List<SkillDTO> skillsDTO) {
        List<Skill> skills = new ArrayList<>();
        for (SkillDTO skillDTO : skillsDTO)
            skills.add(new Skill((long) skillDTO.getId(), skillDTO.getSkill()));
        List<UserEntity> employeesWithSkill = skillService.searchBySkill(skills);
        return new ResponseEntity<>(employeesWithSkill,HttpStatus.OK);
    }

}
