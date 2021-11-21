package com.proiectcolectiv.proiect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void send(String from, String to, String title, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(body);
        javaMailSender.send(message);
    }

    public void emailSupervisor(String email){
        send(email, "proiectcolectiv2021@gmail.com", "Account awaiting approval", "The user " + email + " has requested the approval of their account");
    }

    public void emailCreatedUser(String email){
        send(email, "proiectcolectiv2021@gmail.com", "Account created!", "The account " + email + " has been created");
    }

    public void emailFailedLogin(String email){
        send("proiectcolectiv2021@gmail.com", email, "Failed login attempt!", "We have recorded one failed login attempt for the account " + email);
    }
}