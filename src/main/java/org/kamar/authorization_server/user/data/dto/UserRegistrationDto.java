package org.kamar.authorization_server.user.data.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * DTO for user registration.
 * @author kamar baraka.*/

public record UserRegistrationDto (
        @NonNull
        String username,
        String firstname,
        String lastname,
        @NonNull
        String password,
        @NotNull
        List<String > scopes
) {
}
