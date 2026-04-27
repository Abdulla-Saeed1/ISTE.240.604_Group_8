# FitReserve — ISTE.240.604 Group 8

A fitness class and personal training booking platform built with Spring Boot and MySQL.

## Group Members

| Name | Student ID | Entity Owned |
|---|---|---|
| Abdulla Almarri | 744000213 | User |
| Khalifa Alhammadi | 399001487 | Trainer |
| Ujjwal Jain | 746007518 | FitnessSession |
| Hamad Bin Dasmal | 743009877 | Booking |

## Tech Stack

- **Backend:** Java 17, Spring Boot 3.5, Spring Data JPA
- **Database:** MySQL 8
- **Frontend:** HTML5 + JavaScript (Fetch API)
- **Build:** Maven

## REST API Endpoints

| Method | URL | Description | Owner |
|---|---|---|---|
| GET | /api/users | List all users | Abdulla Almarri (744000213) |
| POST | /api/users | Add a new user | Abdulla Almarri (744000213) |
| GET | /api/trainers | List all trainers | Khalifa Alhammadi (399001487) |
| POST | /api/trainers | Add a new trainer | Khalifa Alhammadi (399001487) |
| GET | /api/sessions | List all sessions | Ujjwal Jain (746007518) |
| POST | /api/sessions | Add a new session | Ujjwal Jain (746007518) |
| GET | /api/bookings | List all bookings | Hamad Bin Dasmal (743009877) |
| POST | /api/bookings | Add a new booking | Hamad Bin Dasmal (743009877) |

## Project Structure

```
Project/src/main/java/edu/rit/project/
├── ProjectApplication.java
├── model/
│   ├── User.java                       (Abdulla Almarri - 744000213)
│   ├── Trainer.java                    (Khalifa Alhammadi - 399001487)
│   ├── FitnessSession.java             (Ujjwal Jain - 746007518)
│   └── Booking.java                    (Hamad Bin Dasmal - 743009877)
├── repository/
│   ├── UserRepository.java             (Abdulla Almarri - 744000213)
│   ├── TrainerRepository.java          (Khalifa Alhammadi - 399001487)
│   ├── FitnessSessionRepository.java   (Ujjwal Jain - 746007518)
│   └── BookingRepository.java          (Hamad Bin Dasmal - 743009877)
├── service/
│   ├── UserService.java                (Abdulla Almarri - 744000213)
│   ├── TrainerService.java             (Khalifa Alhammadi - 399001487)
│   ├── FitnessSessionService.java      (Ujjwal Jain - 746007518)
│   └── BookingService.java             (Hamad Bin Dasmal - 743009877)
└── controller/
    ├── UserController.java             (Abdulla Almarri - 744000213)
    ├── TrainerController.java          (Khalifa Alhammadi - 399001487)
    ├── FitnessSessionController.java   (Ujjwal Jain - 746007518)
    └── BookingController.java          (Hamad Bin Dasmal - 743009877)
```