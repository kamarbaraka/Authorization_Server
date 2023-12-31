package org.kamar.authorization_server.scope.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
    private final CacheControl cacheControl = CacheControl.maxAge(2, TimeUnit.DAYS);

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
    public ResponseEntity<ScopeModel> createScope(@RequestBody @Validated Scope scope) {

        /*create the scope*/
        Scope createdScope = service.createScope(scope);
        ScopeModel scopeModel = assembler.toModel(createdScope);

        return ResponseEntity.created(scopeModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(scopeModel);
    }

    @GetMapping(value = {"{username}"})
    @Operation(
                tags = {"Scope Management."},
                summary = "Api to get user scopes.",
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

    @GetMapping
    @Operation(
                tags = {"Scope Management."},
                summary = "Api to get a scope by authority.",
                description = "Get a scope by its authority.",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
                parameters = {},
                responses = {},
                security = {},
                servers = {}
    )
    public ResponseEntity<ScopeModel> getScopeByAuthority(@RequestParam(name = "authority") final String authority){

        /*get the scope*/
        Scope scope = service.getScopeByAuthority(authority);
        /*model the scope*/
        ScopeModel scopeModel = assembler.toModel(scope);

        /*respond*/
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .lastModified(scope.getUpdatedOn())
                .body(scopeModel);
    }
}
