package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
