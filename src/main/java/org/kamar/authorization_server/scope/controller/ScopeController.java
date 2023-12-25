package org.kamar.authorization_server.scope.controller;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.data.hateoas.ScopeModelAssembler;
import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.scope.service.ScopeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"api/v1/scopes"})
@RequiredArgsConstructor
public class ScopeController implements ScopeApi{

    private final ScopeService service;
    private final ScopeModelAssembler assembler;

    @PostMapping
    @Override
    public ResponseEntity<ScopeModel> createScope(@RequestBody Scope scope) {

        /*create the scope*/
        Scope createdScope = service.createScope(scope);
        ScopeModel scopeModel = assembler.toModel(createdScope);

        return ResponseEntity.status(HttpStatus.CREATED).body(scopeModel);
    }
}
