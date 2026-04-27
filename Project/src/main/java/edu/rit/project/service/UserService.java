package edu.rit.project.service;

/*
 * Author: Abdulla Almarri (ID: 744000213)
 * Service class managing User data and business logic for the FitReserve system.
 */

import edu.rit.project.model.User;
import edu.rit.project.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void seedUsers() {
        if (userRepository.count() == 0) {
            userRepository.save(new User(101, "Ahmad", "Ahmad@Gmail.com", 551234567, "Ahmad-131", "Standard"));
            userRepository.save(new User(102, "Khalid", "Khalid@Gmail.com", 551225432, "Khal1d-21", "Premium"));
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(int id, User updated) {
        return userRepository.findById(id).map(user -> {
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            user.setPhoneNumber(updated.getPhoneNumber());
            user.setPassword(updated.getPassword());
            user.setMembershipType(updated.getMembershipType());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Search users by name (uses JPQL @Query from UserRepository)
    public List<User> searchByName(String name) {
        return userRepository.findByNameContaining(name);
    }
}
