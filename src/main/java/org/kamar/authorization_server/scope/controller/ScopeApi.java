package org.kamar.authorization_server.scope.controller;

import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * the scope Api contract.
 *
 * @author kamar baraka.*/

public interface ScopeApi {

    ResponseEntity<ScopeModel> createScope(Scope scope);
    ResponseEntity<List<ScopeModel>> getUserScopesByUsername(String username);
}
