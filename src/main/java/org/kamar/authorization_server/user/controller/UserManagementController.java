package org.kamar.authorization_server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kamar.authorization_server.user.data.UserRegistrationDto;
import org.kamar.authorization_server.user.service.UserManagementService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
            tags = {"User Management"}, summary = "Api to register a user",
            description = "Register a user through this Api."
    )
    @PreAuthorize("permitAll()")
    @CrossOrigin
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationDto userRegistrationDto){

        /*register the user*/
        userManagementService.registerUser(userRegistrationDto);

        /*respond*/
        return ResponseEntity.noContent().build();
    }
}
