package edu.rit.project.service;/*
 * Author: Ujjwal Jain (ID: 746007518)
 * Service class managing FitnessSession data and business logic for the FitReserve system.
 */

import edu.rit.project.model.FitnessSession;
import edu.rit.project.repository.FitnessSessionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessSessionService {

    @Autowired
    private FitnessSessionRepository sessionRepository;

    @PostConstruct
    public void seedSessions() {
        if (sessionRepository.count() == 0) {
            sessionRepository.save(new FitnessSession(301, "HIIT Training", 201, "2026-03-11", "06:00 PM", 45, 15, "Gym Hall"));
            sessionRepository.save(new FitnessSession(302, "Morning Yoga", 202, "2026-03-10", "09:00 AM", 60, 20, "Studio A"));
        }
    }

    public List<FitnessSession> getSessions() {
        return sessionRepository.findAll();
    }

    public Optional<FitnessSession> getSessionById(int id) {
        return sessionRepository.findById(id);
    }

    public FitnessSession addSession(FitnessSession session) {
        return sessionRepository.save(session);
    }

    public Optional<FitnessSession> updateSession(int id, FitnessSession updated) {
        return sessionRepository.findById(id).map(session -> {
            session.setSessionName(updated.getSessionName());
            session.setTrainerId(updated.getTrainerId());
            session.setDate(updated.getDate());
            session.setTime(updated.getTime());
            session.setDuration(updated.getDuration());
            session.setCapacity(updated.getCapacity());
            session.setLocation(updated.getLocation());
            return sessionRepository.save(session);
        });
    }

    public boolean deleteSession(int id) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Search sessions by location (uses JPQL @Query from FitnessSessionRepository)
    public List<FitnessSession> searchByLocation(String location) {
        return sessionRepository.findByLocation(location);
    }
}