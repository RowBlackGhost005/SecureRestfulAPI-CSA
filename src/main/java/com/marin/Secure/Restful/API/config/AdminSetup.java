package com.marin.Secure.Restful.API.config;

import com.marin.Secure.Restful.API.entities.Role;
import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.repository.RoleRepository;
import com.marin.Secure.Restful.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Manages the initial setup of the admin account.
 *
 * This component will execute only ONCE in the initial deployment of the app and its credentials should be changed
 */
@Component
public class AdminSetup implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        // Check if the Admin user already exists.
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password")); // Hash password

            Role role = roleRepository.findByName("ADMIN").orElseThrow();

            admin.getRoles().add(role);

            userRepository.save(admin);
        }
    }
}

