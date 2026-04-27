package edu.rit.project.controller;

/*
 * Author: Khalifa Alhammadi (ID: 399001487)
 * REST controller exposing all 6 Trainer API endpoints for the FitReserve system.
 */

import edu.rit.project.model.Trainer;
import edu.rit.project.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    // 1. GET all trainers
    @GetMapping
    public ResponseEntity<List<Trainer>> getTrainers() {
        return ResponseEntity.ok(trainerService.getTrainers());
    }

    // 2. GET single trainer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable int id) {
        return trainerService.getTrainerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. GET trainers — search by specialization using query parameter (/api/trainers/search?specialization=xxx)
    @GetMapping("/search")
    public ResponseEntity<List<Trainer>> searchTrainers(@RequestParam String specialization) {
        return ResponseEntity.ok(trainerService.getTrainersBySpecialization(specialization));
    }

    // 4. POST create a new trainer
    @PostMapping
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer) {
        return ResponseEntity.ok(trainerService.addTrainer(trainer));
    }

    // 5. PUT update an existing trainer
    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable int id, @RequestBody Trainer trainer) {
        return trainerService.updateTrainer(id, trainer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. DELETE a trainer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable int id) {
        return trainerService.deleteTrainer(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
