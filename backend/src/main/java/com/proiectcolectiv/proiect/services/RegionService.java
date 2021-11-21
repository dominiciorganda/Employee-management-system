package com.proiectcolectiv.proiect.services;

import com.proiectcolectiv.proiect.entities.Region;
import com.proiectcolectiv.proiect.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class RegionService {

    private final RegionRepository regionRepository;


    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region save(Region region) {
        Optional<Region> optionalRegion;

        optionalRegion = regionRepository.findRegionByRegion(region.getRegion());

        Region p1 =  optionalRegion.isPresent() ? null : regionRepository.save(region);

        return ofNullable(p1)
                .orElseThrow(() -> new EntityExistsException("Region already exist: " + region.getRegion()));
    }

}
