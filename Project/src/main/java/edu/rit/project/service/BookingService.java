package edu.rit.project.service;

/*
 * Author: Hamad Bin Dasmal (ID: 743009877)
 * Service class managing Booking data and business logic for the FitReserve system.
 */

import edu.rit.project.model.Booking;
import edu.rit.project.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @PostConstruct
    public void seedBookings() {
        if (bookingRepository.count() == 0) {
            bookingRepository.save(new Booking(401, 101, 301, "2026-03-05", "Confirmed"));
            bookingRepository.save(new Booking(402, 102, 302, "2026-03-06", "Pending"));
        }
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(int id, Booking updated) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setUserId(updated.getUserId());
            booking.setSessionId(updated.getSessionId());
            booking.setBookingDate(updated.getBookingDate());
            booking.setStatus(updated.getStatus());
            return bookingRepository.save(booking);
        });
    }

    public boolean deleteBooking(int id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }
}