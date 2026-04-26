package edu.rit.project.service;

/*
 * Author: Khalifa Alhammadi (ID: 399001487)
 * Service class managing Trainer data and business logic for the FitReserve system.
 */

import edu.rit.project.model.Trainer;
import edu.rit.project.repository.TrainerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @PostConstruct
    public void seedTrainers() {
        if (trainerRepository.count() == 0) {
            trainerRepository.save(new Trainer("Omar Ali", 201, "Strength Training", 6, "Sunday-Thursday", 4.6));
            trainerRepository.save(new Trainer("Sara Hassan", 202, "Yoga", 5, "Monday-Friday", 4.8));
        }
    }

    public List<Trainer> getTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(int id) {
        return trainerRepository.findById(id);
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Optional<Trainer> updateTrainer(int id, Trainer updated) {
        return trainerRepository.findById(id).map(trainer -> {
            trainer.setName(updated.getName());
            trainer.setSpecialization(updated.getSpecialization());
            trainer.setExperienceYears(updated.getExperienceYears());
            trainer.setAvailabilitySchedule(updated.getAvailabilitySchedule());
            trainer.setRating(updated.getRating());
            return trainerRepository.save(trainer);
        });
    }

    public boolean deleteTrainer(int id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Trainer> getTrainersBySpecialization(String specialization) {
        return trainerRepository.findBySpecialization(specialization);
    }
}