package edu.rit.project.model;

/*
 * Model class representing a trainer in the FitReserve system.
 * This class stores trainer details such as specialization, experience, availability, and rating.
 */

public class Trainer {
    private int trainerId;
    private String name;
    private String specialization;
    private int experienceYears;
    private String availabilitySchedule;
    private double rating;

    // Constructor used to create a new trainer object
    public Trainer(String name, int trainerId, String specialization, int experienceYears, String availabilitySchedule, double rating) {
        this.name = name;
        this.trainerId = trainerId;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.availabilitySchedule = availabilitySchedule;
        this.rating = rating;
    }

    // Getter and setter methods for trainer information
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getAvailabilitySchedule() {
        return availabilitySchedule;
    }

    public void setAvailabilitySchedule(String availabilitySchedule) {
        this.availabilitySchedule = availabilitySchedule;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
