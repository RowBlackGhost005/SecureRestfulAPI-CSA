package com.marin.Secure.Restful.API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistryDTO (
        @NotBlank(message = "You must provide a username")
        String username ,

        @NotBlank(message = "You must provide a password")
        @Size(min = 4 , message = "Password must have at least 4 characters")
        String password
){}
