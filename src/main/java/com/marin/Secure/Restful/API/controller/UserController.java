package com.marin.Secure.Restful.API.controller;

import com.marin.Secure.Restful.API.dto.UserRegistryDTO;
import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> fetchAllUsers(){
        return userService.fetchAllUsers();
    }
}
