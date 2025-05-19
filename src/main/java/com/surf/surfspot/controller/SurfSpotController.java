package com.surf.surfspot.controller;

import com.surf.surfspot.model.SurfSpot;
import com.surf.surfspot.repository.SurfSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@CrossOrigin(origins = "*")
public class SurfSpotController {

    @Autowired
    private SurfSpotRepository repo;

    @GetMapping
    public List<SurfSpot> listarTodos() {
        return repo.findAll();
    }

    @GetMapping("/buscar")
    public List<SurfSpot> buscarPorNome(@RequestParam String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }
}

