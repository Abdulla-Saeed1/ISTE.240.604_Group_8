package edu.rit.project;

import edu.rit.project.model.Booking;
import edu.rit.project.model.FitnessSession;
import edu.rit.project.model.Trainer;
import edu.rit.project.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FitReserveService {
    private List<User> users = new ArrayList<>();
    private List<Trainer> trainers = new ArrayList<>();
    private List<FitnessSession> sessions = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public FitReserveService() {
        users.add(new User(101, "Ahmad", "Ahmad@Gmail.com",0551234567, "Ahmad-131", "Standard"));
        users.add(new User(102,"Khalid","Khalid@Gmail.com",0551225432,"Khal1d-21","Premium" ));

        trainers.add(new Trainer("Omar Ali", 201,"Strength Training",6,"Sunday-Thursday",4.6));
        trainers.add(new Trainer("Sara Hassan", 202,"Yoga",5,"Monday-Friday",4.8));

        sessions.add(new FitnessSession(301, "HIIT Training", 201, "2026-03-11", "06:00 PM", 45, 15, "Gym Hall"));
        sessions.add(new FitnessSession(302, "Morning Yoga", 202, "2026-03-10", "09:00 AM", 60, 20, "Studio A"));

        bookings.add(new Booking(401, 101, 301, "2026-03-05", "Confirmed"));
        bookings.add(new Booking(402, 102, 302, "2026-03-06", "Pending"));

    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public List<FitnessSession> getSessions() {
        return sessions;
    }

    public void addSession(FitnessSession session) {
        sessions.add(session);
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

}
