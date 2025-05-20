package com.marin.Secure.Restful.API.controller;

import com.marin.Secure.Restful.API.dto.UserRegistryDTO;
import com.marin.Secure.Restful.API.security.JwtUtil;
import com.marin.Secure.Restful.API.service.CustomUserDetailsService;
import com.marin.Secure.Restful.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Validated UserRegistryDTO userDTO){
        userService.registerUser(userDTO);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRegistryDTO loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

}
