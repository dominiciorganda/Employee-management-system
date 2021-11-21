package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class AdministratorService {
    @Autowired
    private final UserRepository userRepository;

    public AdministratorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity user) {
        Optional<UserEntity> optionalUser;

        optionalUser = userRepository.findUserByEmail(user.getEmail());

        UserEntity user1 = optionalUser.isPresent() ? null : userRepository.save(user);

        return ofNullable(user1)
                .orElseThrow(() -> new EntityExistsException("User already exist: " + user.getEmail()));
    }
    public UserEntity update(UserEntity user) {
        UserEntity user1 = userRepository.save(user);

        return ofNullable(user1)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with ID: " + user.getId()));
    }

    public Optional<UserEntity> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<UserEntity> findUserEntityById(Long id){
        return userRepository.findUserEntityById(id);
    }
}
