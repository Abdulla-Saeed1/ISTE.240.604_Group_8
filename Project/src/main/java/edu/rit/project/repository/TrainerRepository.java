package edu.rit.project.repository;

/*
 * Author: Khalifa Alhammadi (ID: 399001487)
 * JPA repository for Trainer entities. Extends JpaRepository which provides
 * findAll, findById, save, saveAll, deleteById, delete, count, existsById, and more.
 */

import edu.rit.project.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    // Spring Data derived queries
    List<Trainer> findBySpecialization(String specialization);
    List<Trainer> findByRatingGreaterThanEqual(double rating);
    List<Trainer> findByExperienceYearsGreaterThanEqual(int years);

    // Custom JPQL query — findBy[Field] using @Query
    @Query("SELECT t FROM Trainer t WHERE t.specialization = :specialization")
    List<Trainer> findBySpecializationQuery(@Param("specialization") String specialization);

    // Update rating by ID using @Modifying + @Query
    @Modifying
    @Query("UPDATE Trainer t SET t.rating = :rating WHERE t.trainerId = :id")
    int updateRatingById(@Param("id") int id, @Param("rating") double rating);

    // deleteById is inherited from JpaRepository
}