package org.kamar.authorization_server.scope.controller;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.data.dto.ScopeDto;
import org.kamar.authorization_server.scope.data.hateoas.ScopeModelAssembler;
import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.scope.service.ScopeService;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = {"api/v1/scopes"})
@RequiredArgsConstructor
public class ScopeManagementController implements ScopeApi{

    private final ScopeService service;
    private final ScopeModelAssembler assembler;
    private final Scope scope;
    private final CacheControl cacheControl = CacheControl.maxAge(2, TimeUnit.DAYS);

    /**
     * Creates an application scope (granted authority) based on the provided scopeDto.
     *
     * @param scopeDto The DTO object containing the data for the new scope.
     * @return Returns a ResponseEntity containing the created ScopeModel if successful,
     *         or an appropriate error response if unsuccessful.
     */
    @PostMapping
    @Operation(
            tags = {"Scope Management."},
            summary = "Api for creating an application scope (granted authority).",
            description = "create an application scope",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
            parameters = {},
            responses = {},
            security = {},
            servers = {}
    )
    @Override
    public ResponseEntity<ScopeModel> createScope(@RequestBody @Validated ScopeDto scopeDto) {

        /*create the scope*/
        scope.setAuthority(scopeDto.authority());
        Scope createdScope = service.createScope(scope);
        ScopeModel scopeModel = assembler.toModel(createdScope);

        return ResponseEntity.created(scopeModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(scopeModel);
    }

    /**
     * Retrieves the scopes of a user based on their username.
     *
     * @param username The username of the user.
     * @return A ResponseEntity with a list of ScopeModel objects representing the user's scopes.
     */
    @GetMapping(value = {"{username}"})
    @Operation(
                tags = {"Scope Management."},
                summary = "Retrieves the scopes of a user based on their username.",
                description = "Get ``user scopes`` by their username.",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
                parameters = {},
                responses = {},
                security = {},
                servers = {}
    )
    @Override
    public ResponseEntity<List<ScopeModel>> getUserScopesByUsername(@PathVariable("username") final String username) {

        /*get the scopes*/
        List<Scope> userScopes = service.getUserScopesByUsername(username);
        /*create the model of the scopes*/
        List<ScopeModel> scopeModels = userScopes.stream().map(assembler::toModel).toList();

        /*create a response*/
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(scopeModels);
    }

    /**
     * Retrieves a scope by its authority.
     *
     * @param authority the authority of the scope to retrieve
     * @return a ResponseEntity containing the scope model if found, or an empty response if not found
     */
    @GetMapping
    @Operation(
                tags = {"Scope Management."},
                summary = "Api to get a scope by authority.",
                description = "Retrieves a scope by its authority.",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
                parameters = {},
                responses = {},
                security = {},
                servers = {}
    )
    public ResponseEntity<ScopeModel> getScopeByAuthority(@RequestParam(name = "authority") final String authority){

        /*get the scopeEntity*/
        Scope scopeEntity = service.getScopeByAuthority(authority);
        /*model the scopeEntity*/
        ScopeModel scopeModel = assembler.toModel(scopeEntity);

        /*respond*/
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .lastModified(scopeEntity.getUpdatedOn())
                .body(scopeModel);
    }

    public ResponseEntity<Void> fallBack(String authority, RequestNotPermitted ex){

        /*respond with too many requests status*/
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .build();
    }
}
