package edu.rit.project.repository;

/*
 * Author: Hamad Bin Dasmal (ID: 743009877)
 * JPA repository for Booking entities. Extends JpaRepository which provides
 * findAll, findById, save, saveAll, deleteById, delete, count, existsById, and more.
 */

import edu.rit.project.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Spring Data derived queries
    List<Booking> findByUserId(int userId);
    List<Booking> findBySessionId(int sessionId);
    long countByStatus(String status);

    // Custom JPQL query — findBy[Field] using @Query
    @Query("SELECT b FROM Booking b WHERE b.status = :status")
    List<Booking> findByStatus(@Param("status") String status);

    // Update status by ID using @Modifying + @Query
    @Modifying
    @Query("UPDATE Booking b SET b.status = :status WHERE b.bookingId = :id")
    int updateStatusById(@Param("id") int id, @Param("status") String status);

    // deleteById is inherited from JpaRepository
}