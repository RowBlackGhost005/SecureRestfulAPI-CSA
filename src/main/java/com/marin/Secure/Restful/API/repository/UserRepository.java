package com.marin.Secure.Restful.API.repository;

import com.marin.Secure.Restful.API.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
