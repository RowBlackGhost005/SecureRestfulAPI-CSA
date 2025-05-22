package com.marin.Secure.Restful.API.repository;

import com.marin.Secure.Restful.API.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for access Roles data from the database.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
