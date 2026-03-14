package edu.rit.project;

import edu.rit.project.model.Booking;
import edu.rit.project.model.FitnessSession;
import edu.rit.project.model.Trainer;
import edu.rit.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FitReserveController {

    @Autowired
    private FitReserveService service;


    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", service.getUsers());
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserPage() {
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam int userId,
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam int phoneNumber,
                          @RequestParam String password,
                          @RequestParam String membershipType) {
        service.addUser(new User(userId, name, email, phoneNumber, password, membershipType));
        return "redirect:/add/success/user";
    }

    @GetMapping("/trainers")
    public String showTrainers(Model model) {

        model.addAttribute("trainers", service.getTrainers());

        return "trainers";
    }

    @GetMapping("/trainers/add")
    public String addTrainerPage() {
        return "add-trainer";
    }

    @PostMapping("/trainers/add")
    public String addTrainer(@RequestParam String name,
                             @RequestParam int trainerId,
                             @RequestParam String specialization,
                             @RequestParam int experienceYears,
                             @RequestParam String availabilitySchedule,
                             @RequestParam double rating) {

        service.addTrainer(new Trainer(name, trainerId, specialization,experienceYears,availabilitySchedule,rating));

        return "redirect:/add/success/trainer";
    }

    @GetMapping("/sessions")
    public String showSessions(Model model) {
        model.addAttribute("sessions", service.getSessions());
        return "sessions";
    }

    @GetMapping("/sessions/add")
    public String addSessionPage() {
        return "add-session";
    }

    @PostMapping("/sessions/add")
    public String addSession(@RequestParam int sessionId,
                             @RequestParam String sessionName,
                             @RequestParam int trainerId,
                             @RequestParam String date,
                             @RequestParam String time,
                             @RequestParam int duration,
                             @RequestParam int capacity,
                             @RequestParam String location) {

        service.addSession(new FitnessSession(sessionId, sessionName, trainerId, date, time, duration, capacity, location));
        return "redirect:/add/success/session";
    }

    @GetMapping("/bookings")
    public String showBookings(Model model) {

        model.addAttribute("bookings", service.getBookings());

        return "bookings";
    }

    @GetMapping("/bookings/add")
    public String addBookingPage() {

        return "add-booking";
    }

    @PostMapping("/bookings/add")
    public String addBooking(@RequestParam int bookingId,
                             @RequestParam int userId,
                             @RequestParam int sessionId,
                             @RequestParam String bookingDate,
                             @RequestParam String status) {

        service.addBooking(new Booking(bookingId, userId, sessionId, bookingDate, status));

        return "redirect:/add/success/booking";
    }

    @GetMapping("/add/success/{entity}")
    public String showSuccessPage(@PathVariable String entity, Model model) {
        model.addAttribute("entity",entity);
        return "success";
    }
}
