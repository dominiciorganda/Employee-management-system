package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity getUserByEmail(String email);
    List<UserEntity> findAll();
    Optional<UserEntity> findUserEntityByProfilePicName(String name);
    Optional<UserEntity> findUserEntityById(Long id);
}
