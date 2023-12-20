package org.kamar.authorization_server.user.data.dto;

import org.springframework.lang.NonNull;

/**
 * DTO for user registration.
 * @author kamar baraka.*/

public record UserRegistrationDto (
        @NonNull
        String username,
        String firstname,
        String lastname,
        @NonNull
        String password
) {
}
