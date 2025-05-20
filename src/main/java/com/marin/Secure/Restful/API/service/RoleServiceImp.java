package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp {

    @Autowired
    private RoleRepository roleRepository;


}
