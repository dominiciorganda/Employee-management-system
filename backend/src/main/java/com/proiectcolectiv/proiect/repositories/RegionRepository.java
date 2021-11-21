package com.proiectcolectiv.proiect.repositories;

import com.proiectcolectiv.proiect.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findAll();
    Optional<Region> findRegionByRegion(String region);
}
