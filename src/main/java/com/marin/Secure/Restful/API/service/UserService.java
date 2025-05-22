package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.dto.UserDataDTO;
import com.marin.Secure.Restful.API.dto.UserRegistryDTO;
import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.exception.NoUserFoundException;

import java.util.List;

/**
 * Interface for the User service
 */
public interface UserService {

    User registerUser(UserRegistryDTO userRegistry);

    void deleteUser(int id);

    UserDataDTO fetchUserByName(String name) throws NoUserFoundException;

    List<UserDataDTO> fetchAllUsers();
}
