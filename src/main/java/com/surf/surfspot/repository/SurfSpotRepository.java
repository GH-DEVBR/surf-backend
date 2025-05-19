package com.surf.surfspot.repository;

import com.surf.surfspot.model.SurfSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurfSpotRepository extends JpaRepository<SurfSpot, Long> {
    List<SurfSpot> findByNomeContainingIgnoreCase(String nome);
}

