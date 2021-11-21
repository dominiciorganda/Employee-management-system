package com.proiectcolectiv.proiect.controllers;

import com.proiectcolectiv.proiect.services.EmailServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/sendmail")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;
    @RequestMapping(value = "/verifyAccount", method = RequestMethod.POST)
    public ResponseEntity<?> emailSupervisor(@RequestParam String email){
        emailService.emailSupervisor(email);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/userRegistered", method = RequestMethod.POST)
    public ResponseEntity<?> emailUserCreated(@RequestParam String email){
        emailService.emailCreatedUser(email);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/resetAccount", method = RequestMethod.POST)
    public ResponseEntity<?> emailFailedLogin(@RequestParam String email){
        emailService.emailFailedLogin(email);
        return ResponseEntity.ok().build();
    }
}