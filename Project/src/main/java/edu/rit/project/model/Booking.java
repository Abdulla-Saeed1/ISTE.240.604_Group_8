package edu.rit.project.model;

/*
 * Author: Hamad Bin Dasmal (ID: 743009877)
 * Model class representing a booking in the FitReserve system.
 * Links a user to a fitness session using userId and sessionId.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    private int bookingId;
    private int userId;
    private int sessionId;
    private String bookingDate;
    private String status;

    public Booking() {}

    public Booking(int bookingId, int userId, int sessionId, String bookingDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}