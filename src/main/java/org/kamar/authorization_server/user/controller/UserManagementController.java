package org.kamar.authorization_server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.data.hateoas.UserModelAssembler;
import org.kamar.authorization_server.user.data.model.UserModel;
import org.kamar.authorization_server.user.entity.User;
import org.kamar.authorization_server.user.service.UserManagementService;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private final UserModelAssembler userModelAssembler;


    @PostMapping(name = "reg", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            tags = {"User Management."}, summary = "Api to register a user",
            description = "Register a user through this Api."
    )
//    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    public ResponseEntity<UserModel> registerUser(@RequestBody @Validated UserRegistrationDto userRegistrationDto) {

        /*register the user*/
        User user = userManagementService.registerUser(userRegistrationDto);
        /*get the model*/
        UserModel userModel = userModelAssembler.toModel(user);

        /*respond*/
        return ResponseEntity.created(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .build();
    }

    @GetMapping(value = {"{username}"})
    @Operation(
                tags = {"User Management."},
                summary = "Api to get a user by username.",
                description = "Get a user's ``user details`` by their username.",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
                parameters = {},
                responses = {},
                security = {@SecurityRequirement(name = "oauth2", scopes = {"profile", "openid"})},
                servers = {}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserModel> getUserByUsername(@PathVariable("username") final String  username){

        /*get the user*/
        User user = userManagementService.getUserByUsername(username);

        /*create a model*/
        UserModel userModel = userModelAssembler.toModel(user);

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(2, TimeUnit.DAYS))
                .lastModified(user.getUpdatedOn())
                .body(userModel);
    }
}
