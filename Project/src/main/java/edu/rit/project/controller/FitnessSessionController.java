package edu.rit.project.controller;

/*
 * Author: Ujjwal Jain (ID: 746007518)
 * REST controller exposing all 6 FitnessSession API endpoints for the FitReserve system.
 */

import edu.rit.project.model.FitnessSession;
import edu.rit.project.service.FitnessSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class FitnessSessionController {

    @Autowired
    private FitnessSessionService sessionService;

    // 1. GET all sessions
    @GetMapping
    public ResponseEntity<List<FitnessSession>> getSessions() {
        return ResponseEntity.ok(sessionService.getSessions());
    }

    // 2. GET single session by ID
    @GetMapping("/{id}")
    public ResponseEntity<FitnessSession> getSessionById(@PathVariable int id) {
        return sessionService.getSessionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. GET sessions — search by location using query parameter (/api/sessions/search?location=xxx)
    @GetMapping("/search")
    public ResponseEntity<List<FitnessSession>> searchSessions(@RequestParam String location) {
        return ResponseEntity.ok(sessionService.searchByLocation(location));
    }

    // 4. POST create a new session
    @PostMapping
    public ResponseEntity<FitnessSession> addSession(@RequestBody FitnessSession session) {
        return ResponseEntity.ok(sessionService.addSession(session));
    }

    // 5. PUT update an existing session
    @PutMapping("/{id}")
    public ResponseEntity<FitnessSession> updateSession(@PathVariable int id, @RequestBody FitnessSession session) {
        return sessionService.updateSession(id, session)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. DELETE a session
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable int id) {
        return sessionService.deleteSession(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}