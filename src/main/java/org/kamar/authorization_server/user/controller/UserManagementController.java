package org.kamar.authorization_server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.data.model.UserModel;
import org.kamar.authorization_server.user.exception.UserException;
import org.kamar.authorization_server.user.service.UserManagementService;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<UserModel> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {

        /*register the user*/
        try {
            userManagementService.registerUser(userRegistrationDto);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        /*respond*/
        return ResponseEntity.status(HttpStatus.CREATED)
                .cacheControl(CacheControl.maxAge(2, TimeUnit.DAYS))
                .eTag(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))
                .build();
    }

    @GetMapping(value = {"{userId}"})
    public ResponseEntity<List<Scope>> getAuthoritiesByUserId(@PathVariable("userId") long userId){

        return ResponseEntity.ok().build();
    }
}
