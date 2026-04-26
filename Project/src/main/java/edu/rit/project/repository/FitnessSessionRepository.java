package edu.rit.project.repository;

/*
 * Author: Ujjwal Jain (ID: 746007518)
 * JPA repository for FitnessSession entities. Extends JpaRepository which provides
 * findAll, findById, save, saveAll, deleteById, delete, count, existsById, and more.
 */

import edu.rit.project.model.FitnessSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessSessionRepository extends JpaRepository<FitnessSession, Integer> {

    // Spring Data derived queries
    List<FitnessSession> findByTrainerId(int trainerId);
    List<FitnessSession> findByCapacityGreaterThanEqual(int capacity);
    List<FitnessSession> findBySessionNameContainingIgnoreCase(String name);

    // Custom JPQL query — findBy[Field] using @Query
    @Query("SELECT s FROM FitnessSession s WHERE s.location = :location")
    List<FitnessSession> findByLocation(@Param("location") String location);

    // Update capacity by ID using @Modifying + @Query
    @Modifying
    @Query("UPDATE FitnessSession s SET s.capacity = :capacity WHERE s.sessionId = :id")
    int updateCapacityById(@Param("id") int id, @Param("capacity") int capacity);

    // deleteById is inherited from JpaRepository
}
