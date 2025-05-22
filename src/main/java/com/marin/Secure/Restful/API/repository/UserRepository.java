package com.marin.Secure.Restful.API.repository;

import com.marin.Secure.Restful.API.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for access User's data from the database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
