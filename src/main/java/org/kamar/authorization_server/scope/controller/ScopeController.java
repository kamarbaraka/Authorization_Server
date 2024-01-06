package org.kamar.authorization_server.scope.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.data.hateoas.ScopeModelAssembler;
import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.scope.service.ScopeService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = {"api/v1/scopes"})
@RequiredArgsConstructor
public class ScopeController implements ScopeApi{

    private final ScopeService service;
    private final ScopeModelAssembler assembler;

    @PostMapping
    @Operation(
            tags = {"Scope Management"},
            summary = "Api for creating an application scope (granted authority).",
            description = "create an application scope",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
            parameters = {},
            responses = {},
            security = {},
            servers = {}
    )
    @Override
    public ResponseEntity<ScopeModel> createScope(@RequestBody Scope scope) {

        /*create the scope*/
        Scope createdScope = service.createScope(scope);
        ScopeModel scopeModel = assembler.toModel(createdScope);

        return ResponseEntity.status(HttpStatus.CREATED).body(scopeModel);
    }

    @Operation(
                tags = {"Scope Management"},
                summary = "Api to get user scopes.",
                description = "Get ``user scopes`` by their username.",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
                parameters = {},
                responses = {},
                security = {},
                servers = {}
    )
    @Override
    public ResponseEntity<List<ScopeModel>> getUserScopesByUsername(String username) {

        /*get the scopes*/
        List<Scope> userScopes = service.getUserScopesByUsername(username);
        /*create the model of the scopes*/
        List<ScopeModel> scopeModels = userScopes.stream().map(assembler::toModel).toList();

        /*create a response*/
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(2, TimeUnit.DAYS))
                .body(scopeModels);
    }
}
