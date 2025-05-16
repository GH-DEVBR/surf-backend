package com.example.surf_backend.controller;

import com.example.surf_backend.model.*;
import com.example.surf_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots/{spotId}/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepo;
    private final SpotRepository spotRepo;

    @PostMapping
    public ResponseEntity<Review> criar(@PathVariable Long spotId, @RequestBody Review review, Authentication auth) {
        Spot spot = spotRepo.findById(spotId).orElseThrow();
        User user = (User) auth.getPrincipal();

        review.setSpot(spot);
        review.setUser(user);
        return ResponseEntity.ok(reviewRepo.save(review));
    }

    @GetMapping
    public List<Review> listar(@PathVariable Long spotId) {
        return reviewRepo.findBySpotId(spotId);
    }
}



