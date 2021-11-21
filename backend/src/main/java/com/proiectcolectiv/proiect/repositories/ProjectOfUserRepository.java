package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProjectOfUserRepository  extends JpaRepository<ProjectOfUser, Long> {
    ArrayList<ProjectOfUser> findProjectOfUserByUser(UserEntity userEntity);
}
