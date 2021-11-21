package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.entities.Technology;
import com.proiectcolectiv.proiect.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;


    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }
    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    public Technology save(Technology technology) {
        Optional<Technology> optionalTechnology;

        optionalTechnology = technologyRepository.findTechnologyByTechnology(technology.getTechnology());

        Technology p1 = optionalTechnology.isPresent() ? null : technologyRepository.save(technology);

        return ofNullable(p1)
                .orElseThrow(() -> new EntityExistsException("Technology already exist: " + technology.getTechnology()));
    }

}
