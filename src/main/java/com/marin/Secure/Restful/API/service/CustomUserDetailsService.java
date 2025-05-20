package com.marin.Secure.Restful.API.service;

import com.marin.Secure.Restful.API.entities.Role;
import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role r : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() , grantedAuthorities);
    }
}
