package com.proiectcolectiv.proiect.controllers;

import com.proiectcolectiv.proiect.dtos.ProjectExperienceDTO;
import com.proiectcolectiv.proiect.entities.*;
import com.proiectcolectiv.proiect.services.*;
import io.swagger.annotations.Api;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private SkillService skillService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TechnologyService technologyService;

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/get/project", method = RequestMethod.GET)
    public List<ProjectsEntity> findAll() {
        return projectService.findAll();
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/get/skill", method = RequestMethod.GET)
    public List<Skill> findAllSkills() {
        return skillService.findAll();
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/get/user", method = RequestMethod.GET)
    public UserEntity getUserData(@RequestParam String email) {
        return userService.findOne(email);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/add/skill", method = RequestMethod.POST)
    public SkillOfUser addSkillToUser(@RequestParam Long userId, @RequestParam Long skillId, @RequestParam int grade) {
        return employeeService.saveSkill(userId,skillId,grade);
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/add/project", method = RequestMethod.POST)
    public SkillOfUser addProjectToUser(@RequestBody ProjectExperienceDTO projectExperienceDTO) {
        return employeeService.saveProject(projectExperienceDTO);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/get/userProjects", method = RequestMethod.GET)
    public ArrayList<ProjectOfUser> getUserProjects(@RequestParam Long userId) {
        return employeeService.getUserProjects(userId);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/get/technology", method = RequestMethod.GET)
    public List<Technology> findAllTechnologies() {
        return technologyService.findAll();
    }

    @RequestMapping(value = "/pdfreport/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfReport(@PathVariable("id") Long id) throws IOException, DocumentException, com.lowagie.text.DocumentException {

        UserEntity userEntity = userService.findById(id).get();
        ByteArrayInputStream bis = GeneratePdfReport.employeeReport(userEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
