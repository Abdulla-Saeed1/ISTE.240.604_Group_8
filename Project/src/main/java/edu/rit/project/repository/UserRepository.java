package edu.rit.project.repository;

/*
 * Author: Abdulla Almarri (ID: 744000213)
 * JPA repository for User entities. Extends JpaRepository which provides
 * findAll, findById, save, saveAll, deleteById, delete, count, existsById, and more.
 */

import edu.rit.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Spring Data derived queries
    List<User> findByMembershipType(String membershipType);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    // Custom JPQL query — findBy[Field] using @Query
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);

    // Update email by ID using @Modifying + @Query
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.userId = :id")
    int updateEmailById(@Param("id") int id, @Param("email") String email);

    // deleteById is inherited from JpaRepository
}
