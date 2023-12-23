package org.kamar.authorization_server.scope.controller;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"api/v1/scopes"})
@RequiredArgsConstructor
public class ScopeController implements ScopeApi{

    private final

    @Override
    public ResponseEntity<ScopeModel> createScope(Scope scope) {
        return null;
    }
}
