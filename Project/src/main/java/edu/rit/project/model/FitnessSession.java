package edu.rit.project.model;

/*
 * Model class representing a fitness session in the FitReserve system.
 * This class stores session details and links each session to a trainer using trainerId.
 */

public class FitnessSession {
    private int sessionId;
    private String sessionName;
    private int trainerId;
    private String date;
    private String time;
    private int duration;
    private int capacity;
    private String location;

    // Constructor used to create a new fitness session object
    public FitnessSession(int sessionId, String sessionName, int trainerId, String date, String time, int duration, int capacity, String location) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.trainerId = trainerId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.capacity = capacity;
        this.location = location;
    }

    // Getter and setter methods for session information
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
