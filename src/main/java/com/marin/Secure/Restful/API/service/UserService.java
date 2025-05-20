package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.dto.UserRegistryDTO;
import com.marin.Secure.Restful.API.entities.User;

import java.util.List;

public interface UserService {

    User registerUser(UserRegistryDTO userRegistry);

    List<User> fetchAllUsers();
}
