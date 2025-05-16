package com.example.surf_backend.controller;

import com.example.surf_backend.model.Spot;
import com.example.surf_backend.repository.SpotRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@CrossOrigin(origins = "*")
public class SpotController {

    private final SpotRepository spotRepository;

    public SpotController(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @GetMapping
    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }

    @GetMapping("/search")
    public List<Spot> searchByName(@RequestParam String nome) {
        return spotRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/{id}")
    public Spot getSpotById(@PathVariable Long id) {
        return spotRepository.findById(id).orElse(null);
    }
}



