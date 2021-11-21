package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.entities.Role;
import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {

    @Autowired
    private final UserRepository userRepository;


    public SupervisorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findById(Long id) {

        return userRepository.findById(id);
    }

    public List<UserEntity> findAllEmployeesBySupervisor(Long id) {
        List<UserEntity> users = userRepository.findAll();
        List<UserEntity> employeesSupervisedBy = new ArrayList<>();
        for (UserEntity user : users) {
            if (user.getIdSupervisor()!=null && user.getIdSupervisor().equals(id) && user.getRole() == Role.EMPLOYEE) {
                employeesSupervisedBy.add(user);
            }
        }
        return employeesSupervisedBy;

    }
}
