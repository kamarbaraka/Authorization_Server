package org.kamar.authorization_server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kamar.authorization_server.exception.ErrorCode;
import org.kamar.authorization_server.exception.UserPresError;
import org.kamar.authorization_server.user.data.UserRegistrationDto;
import org.kamar.authorization_server.user.exception.UserException;
import org.kamar.authorization_server.user.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * the controller for user management operations.
 * @author kamar baraka.*/


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = {"api/v1/users", "users"})
public class UserManagementController {

    private final UserManagementService userManagementService;


    @PostMapping(name = "reg", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            tags = {"User Management."}, summary = "Api to register a user",
            description = "Register a user through this Api."
    )
//    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) throws UserException {

        /*register the user*/
        try {
            userManagementService.registerUser(userRegistrationDto);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        /*respond*/
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
