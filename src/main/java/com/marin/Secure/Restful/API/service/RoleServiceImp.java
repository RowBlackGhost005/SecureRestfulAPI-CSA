package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the role service for managing operations of the role.
 */
@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleRepository roleRepository;


}
