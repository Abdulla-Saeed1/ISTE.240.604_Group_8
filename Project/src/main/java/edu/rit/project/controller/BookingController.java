package edu.rit.project.controller;

/*
 * Author: Hamad Bin Dasmal (ID: 743009877)
 * REST controller exposing all 6 Booking API endpoints for the FitReserve system.
 */

import edu.rit.project.model.Booking;
import edu.rit.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // 1. GET all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }

    // 2. GET single booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. GET bookings — search by status using query parameter (/api/bookings/search?status=xxx)
    @GetMapping("/search")
    public ResponseEntity<List<Booking>> searchBookings(@RequestParam String status) {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(status));
    }

    // 4. POST create a new booking
    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.addBooking(booking));
    }

    // 5. PUT update an existing booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. DELETE a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        return bookingService.deleteBooking(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}