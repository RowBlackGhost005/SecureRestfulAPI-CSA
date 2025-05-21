package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.dto.UserDataDTO;
import com.marin.Secure.Restful.API.dto.UserRegistryDTO;
import com.marin.Secure.Restful.API.entities.Role;
import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.exception.NoUserFoundException;
import com.marin.Secure.Restful.API.repository.RoleRepository;
import com.marin.Secure.Restful.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(UserRegistryDTO userRegistry) {
        User user = new User();
        user.setUsername(userRegistry.username());
        user.setPassword(passwordEncoder.encode(userRegistry.password()));

        Role userRole = roleRepository.findByName("User").orElseThrow();

        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDataDTO fetchUserByName(String username) throws NoUserFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoUserFoundException("No user with such username"));

        return new UserDataDTO(user.getId() , user.getUsername());
    }

    @Override
    public List<UserDataDTO> fetchAllUsers() {
        List<User> usersDB = userRepository.findAll();

        List<UserDataDTO> usersData = new ArrayList<>();

        for(User userDB : usersDB){
            usersData.add(new UserDataDTO(userDB.getId() , userDB.getUsername()));
        }

        return usersData;
    }
}
