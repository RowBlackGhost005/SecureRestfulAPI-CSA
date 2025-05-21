package com.marin.Secure.Restful.API.controller;

import com.marin.Secure.Restful.API.dto.UserDataDTO;
import com.marin.Secure.Restful.API.entities.User;
import com.marin.Secure.Restful.API.exception.NoUserFoundException;
import com.marin.Secure.Restful.API.service.UserService;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public ResponseEntity<UserDataDTO> fetchUserData(@AuthenticationPrincipal UserDetails userDetails) throws NoUserFoundException {
        UserDataDTO userData = userService.fetchUserByName(userDetails.getUsername());

        return ResponseEntity.ok(userData);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/{username}")
    public ResponseEntity<UserDataDTO> fetchUserProfile(@PathVariable String username) throws NoUserFoundException {
        UserDataDTO userData = userService.fetchUserByName(username);

        return ResponseEntity.ok(userData);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserDataDTO>> fetchAllUsers(){
        List<UserDataDTO> usersData = userService.fetchAllUsers();

        return ResponseEntity.ok(usersData);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id ){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNoUserFoundException(NoUserFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error trying to fetch user details: " + ex.getMessage());
    }
}
