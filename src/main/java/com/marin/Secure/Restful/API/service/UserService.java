package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> fetchAllUsers();
}
