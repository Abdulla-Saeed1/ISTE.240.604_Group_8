package edu.rit.project;

import edu.rit.project.model.Booking;
import edu.rit.project.model.FitnessSession;
import edu.rit.project.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FitReserveService {
    private List<User> users = new ArrayList<>();
    private List<FitnessSession> sessions = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public FitReserveService() {
        users.add(new User(101, "Ahmad", "Ahmad@Gmail.com",0551234567, "Ahmad-131", "Standard"));
        users.add(new User(102,"Khalid","Khalid@Gmail.com",0551225432,"Khal1d-21","Premium" ));
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
