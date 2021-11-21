package com.proiectcolectiv.proiect.controllers;

import com.proiectcolectiv.proiect.entities.ProjectsEntity;
import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.services.SupervisorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @PreAuthorize("hasAuthority('SUPERVISOR')")
    @RequestMapping(value = "/getEmployeesOfSupervisor/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAllEmployeesBySupervisor(@PathVariable("id") Long id) {
        List<UserEntity> employeesSupervisedBy = supervisorService.findAllEmployeesBySupervisor(id);
        return new ResponseEntity<>(employeesSupervisedBy, HttpStatus.OK);
    }
}
